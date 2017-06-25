package compiler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by mt on 6/24/17.
 */
public class ControlFlowGraph {
    int nextStartQuad;
    List<Quadruple> quads;
    List<BasicBlock> basicBlocksList;
    ArrayList<ArrayList<Integer>> basicBlockEdges;


    public ControlFlowGraph(List<Quadruple> quads) {
        this.quads = quads;
        nextStartQuad = 1;
    }

    public void makeUnitBasicBlocks()
    {
        //for each label_quad we keep a list of the quads that jump to it
        HashSet labels = new HashSet();
        HashSet jumps = new HashSet();

        //see which quad nums are labels, and which quads jump to that labels
        for (Quadruple quad : quads)
        {
            if(quad.getOp().equals("=") || quad.getOp().equals("#") || quad.getOp().equals("<") || quad.getOp().equals("<=") || quad.getOp().equals(">") || quad.getOp().equals(">=") || quad.getOp().equals("jump"))
            {
                int current_quad = quad.getNum();
                jumps.add(current_quad);

                int label_quad = Integer.parseInt(quad.getResult());//the quad we jump into is a label quad
                labels.add(label_quad);
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

        nextStartQuad = total + 1;

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

    public void optimizeUnit()
    {
        makeUnitBasicBlocks();

        //use constant folding at each block
        int i = 0;
        for (BasicBlock basicBlock : basicBlocksList)
        {
            System.out.println("Block " + i );
            basicBlocksList.get(i).printQuads();
            System.out.println("Edges");
            System.out.println(basicBlockEdges.get(i));
            System.out.println();

            basicBlock.constantFolding();
            i++;
        }
    }


}