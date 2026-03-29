toHex.java








	
		
		


		
	





	
		




/** toHex
 *
 * A function (method) in Java to convert integers (type int) to a hex string.
 * This is one of my early contributions to Java. Back in Jan '96, the
 * java.lang.Integer class lacked a toHexString method. This was a common
 * request on Java newgroups and web pages of Java developers. Instead of
 * making a package, I just wrote a function that people could cut and paste
 * into their programs. Now that java.lang.Integer has a toHexString method,
 * this is obsolete
 *
 * @version 1.00 1996/Feb/02
 * Status: Obsolete. Use toHexString method of java.lang.integer.
 *
 * @author Rajiv Pant (Betul)   http://rajiv.org   betul@rajiv.org
 *
 */


public String toHex(int n)
{
  String h = "" ;
  int r=0;
  int q=0;
  int nn=n ;
  do
  {
    r=nn % 16 ;
    nn= nn / 16 ;
    switch (r)
    {
      case 10: h = "A" + h; break ;
      case 11: h = "B" + h; break ;
      case 12: h = "C" + h; break ;
      case 13: h = "D" + h; break ;
      case 14: h = "E" + h; break ;
      case 15: h = "F" + h; break ;
      default: h = r + h; break ;
    }
  }
while (nn > 0) ;
return h ;
}