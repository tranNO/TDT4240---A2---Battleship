package tdt4240.a2.view;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import tdt4240.a2.R;
import tdt4240.a2.model.WarshipModel;
import tdt4240.a2.variables.StaticVariables;

/**
 * Created by IntelliJ IDEA.
 * User: tk
 * Date: 15.03.12
 * Time: 11.19
 * To change this template use File | Settings | File Templates.
 */
public class WarshipView extends AbstractView {

    private WarshipModel model;
    private boolean dirty;
    private Paint paint = new Paint();
    private StaticVariables variables = StaticVariables.getInstance();

    public WarshipView(WarshipModel model){
        this.model = model;
        dirty = true;
        this.paint = new Paint();
        paint.setColor(Color.RED);
    }

    /**
     *
     */
    public void draw(Canvas canvas){
        // Draw the vessel.
        Rect shipRect = this.model.getRect();
        Drawable d;
        Bitmap bitmap;

        Paint p = new Paint();
        if(this.model.isSelected()){
            p.setColor(Color.GRAY);
            canvas.drawRect(shipRect, p);
        } else {
            if(this.model.isHorizontal()){
                d = variables.getResources().getDrawable(R.drawable.ship_horizontal);
                bitmap = ((BitmapDrawable)d).getBitmap();
                canvas.drawBitmap(bitmap, null, new Rect(shipRect.left,shipRect.top,shipRect.right,shipRect.bottom), p);
            } else {
                d = variables.getResources().getDrawable(R.drawable.ship_vertical);
                bitmap = ((BitmapDrawable)d).getBitmap();
                canvas.drawBitmap(bitmap, null, new Rect(shipRect.left,shipRect.top,shipRect.right,shipRect.bottom), p);
            }
        }
        //canvas.drawRect(this.model.getRect(), paint);

        // DEBUG COLOR
        Paint bombedPaint = new Paint();
        bombedPaint.setColor(Color.BLUE);

        // Retrieve tiles that needs to be repainted
        Rect[] bombedTiles = model.getBombedTiles();
        d = variables.getResources().getDrawable(R.drawable.boat_explo);
        bitmap = ((BitmapDrawable)d).getBitmap();

        // Draw all the bombed tiles one by one.
        for(Rect rect : bombedTiles){
            if(rect != null)
                canvas.drawBitmap(bitmap, null, new Rect(rect.left,rect.top,rect.right,rect.bottom),new Paint());
        }
        dirty = false;
    }

    /**
     *
     * @param timeElapsed
     */
    public void update(long timeElapsed){
        //
    }
}
