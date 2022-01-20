
public class Properties2 extends BoardSpace2
	{

		private boolean developing;
		private int basicRent;
		private int houseHotelPrice;
		private int oneHouseRent;
		private int twoHouseRent;
		private int threeHouseRent;
		private int hotelRent;
		private int numberOfHousesOwned;
		private String color;

		public Properties2(String t, String n, String o, int c, int l, boolean d , int br , int hh, int or , int tr , int thr , int hr, int nOH, String co)
		{
			setType(t);
			setName(n);
			setOwner(o);
			setCost(c);
			setLocation(l);
			developing = d;
			basicRent = br;
			houseHotelPrice = hh;
			oneHouseRent = or;
			twoHouseRent = tr;
			threeHouseRent = thr;
			hotelRent = hr;
			numberOfHousesOwned = nOH;
			color = co;
		}
		
		public int getNumberOfHousesOwned()
			{
				return numberOfHousesOwned;
			}

		public void setNumberOfHousesOwned(int numberOfHousesOwned)
			{
				this.numberOfHousesOwned = numberOfHousesOwned;
			}
		public int getHouseHotelPrice()
			{
				return houseHotelPrice;
			}

		public void setHouseHotelPrice(int houseHotelPrice)
			{
				this.houseHotelPrice = houseHotelPrice;
			}

		public String getColor()
			{
				return color;
			}

		public void setColor(String color)
			{
				this.color = color;
			}

		public boolean isDeveloping()
			{
				return developing;
			}

		public void setDeveloping(boolean developing)
			{
				this.developing = developing;
			}

		public int getBasicRent()
			{
				return basicRent;
			}

		public void setBasicRent(int basicRent)
			{
				this.basicRent = basicRent;
			}

		public int getOneHouseRent()
			{
				return oneHouseRent;
			}

		public void setOneHouseRent(int oneHouseRent)
			{
				this.oneHouseRent = oneHouseRent;
			}

		public int getTwoHouseRent()
			{
				return twoHouseRent;
			}

		public void setTwoHouseRent(int twoHouseRent)
			{
				this.twoHouseRent = twoHouseRent;
			}

		public int getThreeHouseRent()
			{
				return threeHouseRent;
			}

		public void setThreeHouseRent(int threeHouseRent)
			{
				this.threeHouseRent = threeHouseRent;
			}

		public int getHotelRent()
			{
				return hotelRent;
			}

		public void setHotelRent(int hotelRent)
			{
				this.hotelRent = hotelRent;
			}
		
	}
