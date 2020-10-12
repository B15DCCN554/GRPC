package syncer;

import daoImpl.LoadDataDBImpl;

public class MNPSynRedis implements Runnable{
    private int rowNum;
    private int rowCount;

    public MNPSynRedis(int rowNum, int rowCount) {
        this.rowNum = rowNum;
        this.rowCount = rowCount;
    }

    @Override
    public void run() {
        System.out.println("THREAD START");
        LoadDataDBImpl.getInstance().synLimitData(rowNum,rowNum);
        System.out.println("SYN LIMIT DONE");
    }
}
