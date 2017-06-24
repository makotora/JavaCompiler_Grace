package compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 6/24/17.
 */
public class BasicBlock
{
    List<Quadruple> quads;
    List<Integer> incoming_quads;

    public BasicBlock(List<Quadruple> allQuads, int start, int end, List<Integer> incoming_quads)
    {
        this.quads = new ArrayList<Quadruple>();
        System.out.println("\n\nNew basic block!");

        for (int i=start; i<=end; i++)
        {
            //we -1 because the list starts from 0
            System.out.println(allQuads.get(i-1));
            this.quads.add(allQuads.get(i-1));
        }

        this.incoming_quads = incoming_quads;
    }
}
