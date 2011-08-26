  public class RaceInfo
  {
    double[] unitArr;
    String tavName;

    //arr = "0 3 7 4 1 0 0", tname = "tavern"
    public RaceInfo(String arr, String tname)
    {   
      String[] vals = arr.split(" ");
      unitArr = new double[Race.NUMUNITS];
      for(int i = 0; i < Race.NUMUNITS; i++)
        unitArr[i] = new Double(vals[i]);
      tavName = tname;
    }
  }

