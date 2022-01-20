
public class Railroads2 extends BoardSpace2
	{

		private int oneOwnedRent;
		private int twoOwnedRent;
		private int threeOwnedRent;
		private int fourOwnedRent;
		
		public Railroads2(String t, String n, String o, int c, int l, int onR, int twR, int thR, int foR)
		{
			setType(t);
			setName(n);
			setOwner(o);
			setCost(c);
			setLocation(l);
			oneOwnedRent = onR;
			twoOwnedRent = twR;
			threeOwnedRent = thR;
			fourOwnedRent = foR;
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

		public int getThreeOwnedRent()
			{
				return threeOwnedRent;
			}

		public void setThreeOwnedRent(int threeOwnedRent)
			{
				this.threeOwnedRent = threeOwnedRent;
			}

		public int getFourOwnedRent()
			{
				return fourOwnedRent;
			}

		public void setFourOwnedRent(int fourOwnedRent)
			{
				this.fourOwnedRent = fourOwnedRent;
			}
		
	}
