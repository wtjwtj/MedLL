package softdev1.medll;

/**
 * Created by Thot on 15.03.2016.
 */
import android.view.View;

public interface NLevelListItem {

    public boolean isExpanded();
    public void toggle();
    public NLevelListItem getParent();
    public View getView();
}