package desnej.domov.duchodcu.tvprogram;

import org.simpleframework.xml.Element;


public class Porad {

    @Element(required=false)
    public int id;

    @Element(required=false)
    public String nazev;

    @Element(required=false)
    public String popis;

    @Element(required=false)
    public String cas;

    @Element(required=false)
    public int castime;

    @Element(required=false)
    public int delka;

    @Element(required=false)
    public String kategorie;

    @Element(required=false)
    public String televize;

    @Element(required=false)
    public String obraz;

    @Element(required=false)
    public String titulky;

    @Element(required=false)
    public String img;

}