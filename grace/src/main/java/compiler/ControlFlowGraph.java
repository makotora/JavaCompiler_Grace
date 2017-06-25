package compiler;

import compiler.Definition.Variable;
import compiler.SymbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by mt on 6/24/17.
 */
public class ControlFlowGraph {
    int nextStartQuad;
    SymbolTable symbolTable;
    List<Quadruple> quads;
    List<BasicBlock> basicBlocksList;
    ArrayList<ArrayList<Integer>> basicBlockEdges;


    public ControlFlowGraph(List<Quadruple> quads, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.quads = quads;
        nextStartQuad = 1;
    }

    private void makeBasicBlocks()
    {
        //for each label_quad we keep a list of the quads that jump to it
        HashSet labels = new HashSet();
        HashSet jumps = new HashSet();

        //see which quad nums are labels, and which quads jump to that labels
        for (Quadruple quad : quads)
        {
            String op = quad.getOp();
            if(op.equals("=") || op.equals("#") || op.equals("<") || op.equals("<=") || op.equals(">") || op.equals(">=") || op.equals("jump"))
            {
                int current_quad = quad.getNum();
                jumps.add(current_quad);

                int label_quad = Integer.parseInt(quad.getResult());//the quad we jump into is a label quad
                labels.add(label_quad);
            }
            else if (op.equals("call"))//not so sure about this (I thing call is 'like' a jump
            {
                int current_quad = quad.getNum();
                jumps.add(current_quad);
            }
        }


        this.basicBlocksList = new ArrayList<BasicBlock>();//a list of basic blocks
        this.basicBlockEdges = new ArrayList<ArrayList<Integer>>();//a list of edges for each block (which blocks it goes to)

        int total = quads.size();
        int start = nextStartQuad;
        int end = start;

        for (int i=nextStartQuad; i<=total; i++)
        {

            if (jumps.contains(i))//it jumps so it can only be an end of a basic block!
            {
                if (labels.contains(i))//if it is also a label.than it is a block on it own
                {
                    if (start != i)//if there is a "pending" block start
                    {//end the block at the last quad (i-1)
                        end = i - 1;
                        basicBlocksList.add(new BasicBlock(quads, start, end));
                    }

                    basicBlocksList.add(new BasicBlock(quads, i, i));//this quad is both a label and a jump.It is a block in its own
                }
                else
                {
                    end = i;
                    basicBlocksList.add(new BasicBlock(quads, start, end));
                }

                start = i+1;//next quad is a new start
            }
            else if (labels.contains(i) && start != i)//it is not a start of a block but it is a label(new block!)
            {
                end = i - 1;
                basicBlocksList.add(new BasicBlock(quads, start, end));//there is no jump in this block.it "jumps" to the quad right after

                //this quad is a label, its gonna be the start of the next basic block
                start = i;
            }
        }

        //if there are some quads left (they werent put inside a basic block for some reason)
        if (end != total)//if there end of the last block is not the last quad in the list
        {//make a basic block out of all the quads left!
            basicBlocksList.add(new BasicBlock(quads, start, total));//there is no next quad (-1)
        }

        //we are done separating the blocks.now iterate over the block list and save the edges for our graph
        //(for each block note the blocks it goes to)
        int total_blocks = basicBlocksList.size();

        for (int i=0; i<total_blocks; i++)//for each block
        {
            ArrayList<Integer> edges = new ArrayList<Integer>();

            ArrayList<Integer> jumpQuads = basicBlocksList.get(i).getJumpQuads();//get the quad numbers of the quads this block can go to

            //for every quad this block goes to.find the block number it belongs to
            for (Integer jumpQuad : jumpQuads)
            {
                for (int j=0; j<total_blocks; j++)
                {
                    //if this blocks first quad is the quad we are jumping to..than we are jumping to that block!
                    if (basicBlocksList.get(j).firstQuadNum() == jumpQuad)
                    {
                        edges.add(j);//add the edge and break
                        break;
                    }
                }
            }

            basicBlockEdges.add(edges);
        }


    }


    public void printGraph()
    {
        //print basic blocks and edges (debug)
        int i = 0;
        for (BasicBlock basicBlock : basicBlocksList)
        {
            System.out.println("Block " + i );
            basicBlocksList.get(i).printQuads();
            System.out.println("Edges");
            System.out.println(basicBlockEdges.get(i));
            System.out.println();

            i++;
        }
    }


    public void optimize()
    {
        makeBasicBlocks();

        allOptimizations(10);

//        printGraph();

        //At the end of the optimization
        nextStartQuad = quads.size() + 1;//for next optimization call
    }


    //combinations
    private void allOptimizations()
    {
        constantFolding();
        copyPropagation();
        removeUnreachableBlocks();
        deleteDeadTempVarAssignments();
        fixJumps();
        subexpressions();
        deadAssignmentElimination();
    }

    private void CFCP()//constant folding followed by copy propagation
    {
        constantFolding();
        copyPropagation();
    }

    //multiple function calls
    private void allOptimizations(int repeats)
    {
        for (int i=0; i<repeats; i++)
            allOptimizations();
    }

    private void CFCPs(int repeats)
    {
        for (int i=0; i<repeats; i++)
            CFCP();
    }

    private void CFs(int repeats)
    {
        for (int i=0; i<repeats; i++)
            constantFolding();
    }

    private void CPPs(int repeats)
    {
        for (int i=0; i<repeats; i++)
            copyPropagation();
    }

    private void RBBs(int repeats)
    {
        for (int i=0; i<repeats; i++)
            removeUnreachableBlocks();
    }

    //optimization types - functions

    private void constantFolding()
    {
        for (BasicBlock basicBlock : basicBlocksList)
            basicBlock.constantFolding();

        makeBasicBlocks();//remake graph!
    }

    private void copyPropagation()
    {
        for (BasicBlock basicBlock : basicBlocksList)
            basicBlock.copyPropagation();

        makeBasicBlocks();//remake graph!
    }

    private void subexpressions()
    {
        for (BasicBlock basicBlock : basicBlocksList)
            basicBlock.subexpressions();

        makeBasicBlocks();//remake graph!
    }

    private void removeUnreachableBlocks()
    {
        HashSet reachableBlocks = new HashSet();//set of reachable blocks
        reachableBlocks.add(0);//first block is always reachable (unit start)
        int total = basicBlocksList.size();

        for(int i=0; i<total; i++)
        {
            ArrayList<Integer> blockEdges = basicBlockEdges.get(i);

            for (Integer blockEdge : blockEdges) {
                reachableBlocks.add(blockEdge);
            }
        }

        for(int i=1; i<total; i++)
        {
            //for every block besides the first one (it is always reachable!)
            if (!reachableBlocks.contains(i))
                basicBlocksList.get(i).delete();
        }

        makeBasicBlocks();//remake graph!
    }

    private void fixJumps()
    {
        int total = quads.size();
        for (int i=nextStartQuad; i<=total; i++)
        {
            int index = i - 1;//they start from 0
            Quadruple quad = quads.get(index);
            String op = quad.getOp();

            if (op.equals("jump") || op.equals("<") || op.equals("<=") || op.equals(">") || op.equals(">=") || op.equals("=") || op.equals("#"))
            {
                int jumpTarget = Integer.parseInt(quad.getResult());
                if(op.equals("jump"))
                {
                    if (jumpTarget > i) //if we are jumping ahead
                    {//if all quads before the quad we jump are noop.no point in jumping anyway
                        boolean dead = true;
                        for (int k = i; k < jumpTarget - 1; k++) {
                            if (!quads.get(k).getOp().equals("noop")) {
                                dead = false;
                                break;
                            }
                        }
                        if (dead) {
                            deleteQuad(quad);
                            continue;
                        }
                    }
                }

                Quadruple quadTarget = quads.get(jumpTarget-1);
                op = quadTarget.getOp();//inner op name

                if (op.equals("noop"))//jump to the next quad after noop that is NOT noop
                {
                    int j = jumpTarget;
                    while (quads.get(j).getOp().equals("noop"))
                    {
                        j++;
                    }

                    quad.setResult(Integer.toString(j+1));
                }
                else if (op.equals("jump"))//if we are jumping to an other,jump to the quad the next jump jumps to
                {
                    quad.setResult(quadTarget.getResult());
                }
            }
        }
    }


    //sometimes due to optimization, some temp vars are assigned a value, and the temp var is never used below!
    //remove those assignments from the code
    private void deleteDeadTempVarAssignments()
    {
        int total = quads.size();

        for (int i=nextStartQuad; i<=total; i++)
        {
            int index = i - 1;//they start from 0
            Quadruple quad = quads.get(index);

            if (quad.getOp().equals(":="))//if its an assignment
            {
                String var = quad.getResult();

                if (var.startsWith("$") && !var.equals("$$"))//if a temp var is being assigned something
                {
                    boolean isDead = true;//this will remain true if the tempVar is not being used anywhere below

                    for (int j=i+1; j<=total; j++)
                    {
                        int innerIndex = j - 1;
                        Quadruple innedQuad = quads.get(innerIndex);

                        String arg1 = innedQuad.getArg1();
                        String arg2 = innedQuad.getArg2();

                        if (arg1 != null && arg1.equals(var)) {
                            isDead = false;
                            break;
                        }
                        //or
                        if (arg2 != null && arg2.equals(var)) {
                            isDead = false;
                            break;
                        }

                    }

                    if (isDead)//if the temp var is not being used anywhere else after being assigned a value
                        deleteQuad(quad);//delete the assignment, it isnt needed!
                }
            }
        }
    }

    public void deleteQuad(Quadruple quad)
    {
        quad.setOp("noop");
        quad.setArg1("-");
        quad.setArg2("-");
        quad.setResult("-");
    }

    private boolean sameQuads(List<Quadruple> q1, List<Quadruple> q2)
    {
        int s1 = q1.size();
        int s2 = q2.size();

        if (s1 != s2)
            return false;
        else
        {
            for (int i=0; i<s1; i++)
            {
                Quadruple quad1 = q1.get(i);
                Quadruple quad2 = q2.get(i);

                String field1;
                String field2;

                field1 = Integer.toString(quad1.getNum());
                field2 = Integer.toString(quad2.getNum());
                if (field1 != field2)
                    return false;

                field1 = quad1.getOp();
                field2 = quad2.getOp();
                if (field1 != field2)
                    return false;

                field1 = quad1.getArg1();
                field2 = quad2.getArg1();
                if (field1 != field2)
                    return false;

                field1 = quad1.getArg2();
                field2 = quad2.getArg2();
                if (field1 != field2)
                    return false;

                field1 = quad1.getResult();
                field2 = quad2.getResult();
                if (field1 != field2)
                    return false;

            }

            return true;//we didnt return false inside.so they are the same!
        }
    }

    private void deadAssignmentElimination()
    {
        int total = quads.size();

        for (int i=nextStartQuad; i<=total; i++) {
            int index = i - 1;//they start from 0
            Quadruple quad = quads.get(index);

            if (quad.getOp().equals(":="))
            {
                String var = quad.getResult();

                //if it is $$ or [x]
                //or
                //if var is not a temp var and is not local to this unit..dont bother looking if it is dead or not
                //or it is a parameter by reference
                Variable variable = (Variable) symbolTable.lookup(var);
                if (var.equals("$$") || var.startsWith("[") || (variable != null && (!symbolTable.isLocal(variable) || variable.isReference() )))
                    continue;

                boolean isDead = true;

                for (int j=i+1; j<=total; j++)
                {
                    int innerIndex = j - 1;
                    Quadruple innerQuad = quads.get(innerIndex);
                    String innerOp = innerQuad.getOp();

                    if (innerOp.equals(":=") && innerQuad.getResult().equals(var) && isDead == true)
                        break;//dead
                    else if (innerOp.equals(":=") && innerQuad.getArg1().equals(var))
                    {
                        isDead = false;//its being used for an assignment;
                        break;
                    }
                    else if (innerOp.equals("call"))//if another function is called
                    {
                        isDead = false;//lets assume that the function uses the var!In grace it can do that (nested functions)
                        break;
                    }
                    else if (innerOp.equals("array") && (innerQuad.getArg1().equals(var) || innerQuad.getArg2().equals(var)))//if it is used for an array quad
                    {
                        isDead = false;//lets assume that the function uses the var!In grace it can do that (nested functions)
                        break;
                    }
                    else if (isMathOp(innerOp) || isCompareOp(innerOp))
                    {//if it is a op that uses vars..
                        //check if var is being used
                        if (innerQuad.getArg1().equals(var) || innerQuad.getArg2().equals(var))
                        {
                            isDead = false;
                            break;
                        }//var is not used as arg1 or arg2. check if it assigned another value (without using the previous value)
                        else if (innerQuad.getResult().equals(var) && isDead == true)
                            break;//it is dead
                    }
                    else if (isCompareOp(innerOp) || innerOp.equals("jump"))
                    {
                        if (Integer.parseInt(innerQuad.getResult()) <= j)//Dont erase it!Its not dead it goes up again
                        {
                            isDead = false;
                            break;
                        }
                    }
                }

                if (isDead) //if it wasnt used anywhere.delete the assignment
                {
                    //System.out.println("erasing: " + var);
                    deleteQuad(quad);
                }
            }
        }
    }

    private boolean isMathOp(String op)
    {
        if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("div") || op.equals("mod"))
            return true;
        else
            return false;
    }

    private boolean isCompareOp(String op)
    {
        if (op.equals(">") || op.equals(">=") || op.equals("<") || op.equals("<=") || op.equals("=") || op.equals("#"))
            return true;
        else
            return false;
    }

}