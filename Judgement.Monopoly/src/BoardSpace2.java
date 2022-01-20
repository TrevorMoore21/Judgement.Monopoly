
public class BoardSpace2
	{

		private String type, name, owner;
		private int cost, location;
		
		public BoardSpace2()
		{}
		
		public BoardSpace2(String t , String n, String o, int c, int l)
		{
			type = t;
			name = n;
			owner = o;
			cost = c;
			location = l;
		}
		

		public String getType()
			{
				return type;
			}

		public void setType(String type)
			{
				this.type = type;
			}
		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public int getCost()
			{
				return cost;
			}

		public void setCost(int cost)
			{
				this.cost = cost;
			}

		public String getOwner()
			{
				return owner;
			}

		public void setOwner(String owner)
			{
				this.owner = owner;
			}

		public int getLocation()
			{
				return location;
			}

		public void setLocation(int location)
			{
				this.location = location;
			}
		
	}
