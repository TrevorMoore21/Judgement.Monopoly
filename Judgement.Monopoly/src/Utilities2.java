
public class Utilities2 extends BoardSpace2
	{

		private boolean oneOwnedUtility;
		private boolean twoOwnedUtilities;
		
		public Utilities2(String t, String n, String o, int c, int l, boolean ou, boolean tu)
			{
				setType(t);
				setName(n);
				setOwner(o);
				setCost(c);
				setLocation(l);
				oneOwnedUtility = ou;
				twoOwnedUtilities = tu;
			}

		public boolean isOneOwnedUtility()
			{
				return oneOwnedUtility;
			}

		public void setOneOwnedUtility(boolean oneOwnedUtility)
			{
				this.oneOwnedUtility = oneOwnedUtility;
			}

		public boolean isTwoOwnedUtilities()
			{
				return twoOwnedUtilities;
			}

		public void setTwoOwnedUtilities(boolean twoOwnedUtilities)
			{
				this.twoOwnedUtilities = twoOwnedUtilities;
			}

		
		
	}
