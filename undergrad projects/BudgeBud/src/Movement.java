import javax.swing.JComponent;
public class Movement {
	public static void moveLeft(JComponent panel, int fromX, int toX){
		Thread t=new Thread(new Runnable() {
			public void run() {
				for(int x=fromX;x>=toX;x--){
					panel.setBounds(x,panel.getY(),panel.getWidth(),panel.getHeight());
					
				}
			}
		});
		t.start();
	}
	public static void moveRight(JComponent panel,int fromX, int toX){
		Thread t=new Thread(new Runnable() {
			public void run() {
				for(int x=fromX;x<=toX;x++){
					panel.setBounds(x,panel.getY(),panel.getWidth(),panel.getHeight());
					
				}
			}
		});
		t.start();
	}
}
