package softdev1.medll;

/**
 * Created by wjwjwt on 04/01/16.
 */
public class Leitlinie {
    private Titel mTitel;
    private Meta[] mMeta;
    private Autorj[] mAutorj;

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
}
