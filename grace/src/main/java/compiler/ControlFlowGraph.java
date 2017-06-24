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


    public ControlFlowGraph(List<Quadruple> quads) {
        this.quads = quads;
        nextStartQuad = 1;
    }

    public void makeBasicBlocks()
    {
        //for each label_quad we keep a list of the quads that jump to it
        Hashtable<Integer, List<Integer>> labels = new Hashtable<Integer, List<Integer>>();
        HashSet jumps = new HashSet();

        //see which quad nums are labels, and which quads jump to that labels
        for (Quadruple quad : quads)
        {
            if(quad.getOp().equals("=") || quad.getOp().equals("#") || quad.getOp().equals("<") || quad.getOp().equals("<=") || quad.getOp().equals(">") || quad.getOp().equals(">=") || quad.getOp().equals("jump"))
            {
                int current_quad = quad.getNum();
                jumps.add(current_quad);

                int label_quad = Integer.parseInt(quad.getResult());//the quad we jump into is a label quad

                if (!labels.containsKey(label_quad))//if its not in the hashtable of labels yet
                {//create a list of quads for it and add it in the hash table
                    List<Integer> list = new ArrayList();
                    list.add(current_quad);//current quad jumps to that label quad

                    labels.put(label_quad, list);
                }
                else//if its already in the hash of labels, add current quads at the list of incoming jumps
                {
                    labels.get(label_quad).add(current_quad);
                }
            }
        }

        List<BasicBlock> basicBlocksList = new ArrayList<BasicBlock>();


        int total = quads.size();
        int start = nextStartQuad;
        int end = start;
        List<Integer> incoming_quads;

        for (int i=nextStartQuad; i<=total; i++)
        {

            if (jumps.contains(i))//it jumps so it can only be an end of a basic block!
            {
                if (labels.contains(start))//if start of this block is a label
                    incoming_quads = labels.get(start);//get the list of quads jumping to it
                else//just pass an empty list if its no quad jumps to it
                    incoming_quads = new ArrayList<Integer>();

                end = i;
                basicBlocksList.add(new BasicBlock(quads, start, end, incoming_quads));

                start = i+1;//next quad is a new start
            }
            else if (labels.contains(i))//it is a label but deosnt jump anywhere
            {
                if (labels.contains(start))//if start of this block is a label
                    incoming_quads = labels.get(start);//get the list of quads jumping to it
                else//just pass an empty list if its no quad jumps to it
                    incoming_quads = new ArrayList<Integer>();

                end = i - 1;
                basicBlocksList.add(new BasicBlock(quads, start, end, incoming_quads));

                //this quad is a label, its gonna be the start of the next basic block
                start = i;
            }
        }

        //if there are some quads left (they werent put inside a basic block for some reason)
        if (end != total)//if there end of the last block is not the last quad in the list
        {//make a basic block out of all the quads left!
            if (labels.contains(start))//if start of this block is a label
                incoming_quads = labels.get(start);//get the list of quads jumping to it
            else//just pass an empty list if its no quad jumps to it
                incoming_quads = new ArrayList<Integer>();

            basicBlocksList.add(new BasicBlock(quads, start, total - 1, incoming_quads));
        }

        nextStartQuad = total + 1;


    }


}