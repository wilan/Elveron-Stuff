import java.util.*;

public class Orc extends Race
{
	public Orc()
	{
		super("Orc");
	}
	
	public double extraMods()
	{
		return -(taverns*.1);
	}
}
