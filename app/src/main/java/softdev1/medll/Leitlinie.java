package softdev1.medll;

import java.util.Map;
import java.util.Set;

/**
 * Created by wjwjwt on 04/01/16.
 */
public class Leitlinie {
    private Titel mTitel;
    private Meta[] mMeta;
    private Autorj[] mAutorj;
    private  Map<String, Set<String>> mContent;
    public Titel getTitel() {
        return mTitel;
    }

    public void setTitel(Titel titel) {
        mTitel = titel;
    }

    public Meta[] getMeta() {
        return mMeta;
    }

    public void setMeta(Meta[] meta) {
        mMeta = meta;
    }

    public Autorj[] getAutorj() {
        return mAutorj;
    }

    public void setAutorj(Autorj[] autorj) {
        mAutorj = autorj;
    }

    public  Map<String, Set<String>> getmContent(){ return mContent;}
    public String getChapterName(){
        Object[] child= mContent.get("text").toArray();
       return child.toString();
    }
}
