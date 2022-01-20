
public class Cards2 extends BoardSpace2
	{

		private String cardType;
		private String content;
		
		public Cards2(String ca, String co)
		{
			cardType = ca;
			content = co;
		}
			
		public String getCardType()
			{
				return cardType;
			}

		public void setCardType(String cardType)
			{
				this.cardType = cardType;
			}
		
		public String getContent()
			{
				return content;
			}

		public void setContent(String content)
			{
				this.content = content;
			}
		
	}
