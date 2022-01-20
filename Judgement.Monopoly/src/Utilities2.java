
public class Utilities2 extends BoardSpace2
	{

		private int oneOwnedRent;
		private int twoOwnedRent;
		
		public Utilities2(String t, String n, String o, int c, int l, int oR, int tR)
			{
				setType(t);
				setName(n);
				setOwner(o);
				setCost(c);
				setLocation(l);
				oneOwnedRent = oR;
				twoOwnedRent = tR;
			}

		public int getOneOwnedRent()
			{
				return oneOwnedRent;
			}

		public void setOneOwnedRent(int oneOwnedRent)
			{
				this.oneOwnedRent = oneOwnedRent;
			}

		public int getTwoOwnedRent()
			{
				return twoOwnedRent;
			}

		public void setTwoOwnedRent(int twoOwnedRent)
			{
				this.twoOwnedRent = twoOwnedRent;
			}
		
	}
