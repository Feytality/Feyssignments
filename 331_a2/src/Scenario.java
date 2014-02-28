import java.io.Console;
import java.util.Random;

public class Scenario {
	
	public static void main(String[] args)
	{
		Heap h = new Heap();
		Random l_rand = new Random();
		for(int i = 0; i < 10; ++i)
		{
		h.AddElement(l_rand.nextInt(100),"a");
		}
		
		System.out.print(h.RemoveMaxNode().GetWeight());
		
	
	}
	
}
