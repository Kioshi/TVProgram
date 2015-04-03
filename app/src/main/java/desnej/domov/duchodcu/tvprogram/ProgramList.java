package desnej.domov.duchodcu.tvprogram;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;



@Root
public class ProgramList {

    @ElementList(entry="list", inline=true)
    public List<Porad> porady;

}
