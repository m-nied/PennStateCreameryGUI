//NewImage Class For Drawing IceCream Images
package pennstatecreamery;
import java.awt.*;
import javax.swing.JPanel;
					  
public class NewImage extends JPanel{
    Image myImage;
    
    //Default Constructor
    public NewImage(){}
    
    //Creation of Image
    public void IceCreamImage(String i, String type){
        add(new DrawImages(i, type));
    }	 
    
    //Class Usage:
    //((NewImage)jPanel1).IceCreamImage("file","container");
}
	
