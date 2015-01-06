//Main Image Class for Creating an Image
package pennstatecreamery;
import java.awt.*;
import java.awt.event.*;  
					  
class DrawImages extends Canvas implements MouseMotionListener{
    
    //Variables for Location & Image Type
    private Point myLocation;
    private String imageName, imageType;

    public DrawImages(String img, String type){
        imageName = img;
        imageType = type;

        //Checks the Image Type
        if(imageType.matches("container")){
            setSize(22,30);
            setLocation(220,30);
        }else if(imageType.matches("icecream")){
            setSize(27,17);
            setLocation(220,60);
        }else if(imageType.matches("topping")){
            setSize(30,20);
            setLocation(220,40);
        }
        addMouseMotionListener(this);

    }   

    public void mouseMoved(MouseEvent evt){}

    //Event Handling for Clicking an Image
    public void mouseDragged(MouseEvent evt){
        myLocation = getLocation();
        setLocation(myLocation.x + (evt.getX() - 25), myLocation.y + (evt.getY() - 25));
        repaint();
    }

    //Initial Drawing of Image
    public void paint( Graphics g){
        g.drawImage(Toolkit.getDefaultToolkit().getImage(imageName), 0, 0, this);
    }
}
