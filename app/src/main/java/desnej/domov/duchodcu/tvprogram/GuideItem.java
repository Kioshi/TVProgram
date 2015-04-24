package desnej.domov.duchodcu.tvprogram;

/**
 * Created by program2 on 10.4.2015.
 */
public class GuideItem
{
    public int id;
    public String nazev;
    public String popis;
    public String cas;
    public String datum;
    public long castime;
    public int delka;
    public String kategorie;
    public String televize;

    public double getProgress()
    {
        long currTime = System.currentTimeMillis();
        long endTime = (castime + delka) * 1000;

        if (currTime < castime * 1000)
            return 0.0;

        if (endTime < currTime)
            return 100.0;

        return 100.0 - ((double) (endTime - currTime) / (double) (delka * 1000)) * 100.0;
    }
}
