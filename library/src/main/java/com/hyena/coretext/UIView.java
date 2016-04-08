package com.hyena.coretext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzc on 16/4/7.
 */
public class UIView extends View {

    private List<Line> lines = new ArrayList<>();
    private List<ReplaceCell> replaceCells = new ArrayList<>();
    private List<Cell> cells = new ArrayList<Cell>();

    public UIView(Context context) {
        super(context);
        init();
    }

    public UIView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UIView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        cells.add(new TextCell("这", 50));
        cells.add(new TextCell("是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 30));
        cells.add(new ReplaceCell(true));
        cells.add(new TextCell("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 20));
        cells.add(new ReplaceCell(true));
        cells.add(new TextCell("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 30));
        cells.add(new ReplaceCell(true));
        cells.add(new TextCell("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 30));
        cells.add(new ReplaceCell(true));
        cells.add(new TextCell("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 30));
        cells.add(new ReplaceCell(true));
        cells.add(new TextCell("这是一个最好的时代，这是一个最坏的时代；这是一个智慧的年代，这是一个愚蠢的年代；" +
                "这是一个光明的季节，这是一个黑暗的季节；这是希望之春，这是失望之冬；人们面前应有尽有，人们面前一无所有；人们正踏向天堂之路，人们正走向地狱之门。", 30));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        preDraw();
    }

//    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int y = 0;
//        paint.setColor(Color.RED);
//        for (int i = 0; i < getHeight(); i++) {
////            canvas.drawLine(0, y, getWidth(), y, paint);
//            y += 100;
//        }

        if (lines == null || lines.isEmpty())
            return;

        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            for (int j = 0; j < line.cells.size(); j++) {
                line.cells.get(j).draw(canvas);
            }
        }
    }

    private void preDraw(){
        lines.clear();
        replaceCells.clear();

        int leftWidth = getWidth();
        Line line = new Line();
        lines.add(line);
        List<Cell> crossCells = new ArrayList<>();
        int y = 0;
        for (int i=0; i< cells.size(); i++) {
            Cell mainCell = cells.get(i);
            if (mainCell instanceof ReplaceCell) {
//                if (cell.getHeight() > line.getMaxHeight()) {
                    replaceCells.add((ReplaceCell) mainCell);
//                }
                if (mainCell.getWidth() < leftWidth) {
                    mainCell.x = getWidth() - leftWidth;
                    mainCell.lineY = y;
                    leftWidth -= mainCell.getWidth();
                } else {
                    y += line.getMaxHeight();
                    leftWidth = getWidth() - mainCell.getWidth();
                    line = new Line();
                    lines.add(line);
                    crossCells = getCrossCells(y);
                    mainCell.lineY = y;
                }
                line.cells.add(mainCell);
            } else {
                List<Cell> subCells = mainCell.getSubCells();
                if (subCells != null) {
                    for (int j = 0; j < subCells.size(); j++) {
                        Cell cell = subCells.get(j);
                        if (cell.getWidth() < leftWidth) {
                            Cell hitCell;
                            while ((hitCell = getHitCell(crossCells, getWidth() - leftWidth,
                                    cell.getWidth(), y, cell.getHeight())) != null) {
                                leftWidth = leftWidth - hitCell.getWidth() - (hitCell.x - (getWidth() - leftWidth));
                            }

                            if (cell.getWidth() < leftWidth) {
                                cell.x = getWidth() - leftWidth;
                                cell.lineY = y;
                                leftWidth -= cell.getWidth();
                            } else {
                                //TODO
                                y += line.getMaxHeight();
                                crossCells = getCrossCells(y);
                                leftWidth = getWidth() - cell.getWidth();
                                line = new Line();
                                lines.add(line);
                                cell.lineY = y;
                            }
                        } else {
                            //TODO
                            leftWidth = getWidth();
                            crossCells = getCrossCells(y);
                            y += line.getMaxHeight();
                            Cell hitCell;
                            while ((hitCell = getHitCell(crossCells, getWidth() - leftWidth,
                                    cell.getWidth(), y, cell.getHeight())) != null) {
//                                leftWidth = leftWidth - hitCell.getWidth();
                                leftWidth = leftWidth - hitCell.getWidth() - (hitCell.x - (getWidth() - leftWidth));
                            }

                            line = new Line();
                            lines.add(line);
                            cell.lineY = y;
                            cell.x = getWidth() - leftWidth;
                            leftWidth -= cell.getWidth();
                        }

                        line.cells.add(cell);
                    }
                }
            }
        }
        if (line != null) {
            line.getMaxHeight();
        }
    }

    private Cell getHitCell(List<Cell> cells, int x, int width, int y, int height){
        if (cells == null || cells.isEmpty())
            return null;
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);

            if (new Rect(cell.x, cell.lineY, cell.x + cell.getWidth(),
                    cell.lineY + cell.getHeight()).intersect(new Rect(x, y, x+ width, y + height))) {
                return cell;
            }

        }
        return null;
    }

    private List<Cell> getCrossCells(int y){
        if (replaceCells == null || replaceCells.isEmpty()) {
            return null;
        }
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < replaceCells.size(); i++) {
            Cell cell = replaceCells.get(i);
            if (y >= cell.lineY && y <= cell.lineY + cell.getHeight()) {
                cells.add(cell);
            }
        }
        return cells;
    }

    class Line {
        List<Cell> cells = new ArrayList<Cell>();

        public int getMaxHeight(){
            if (cells != null) {
                int maxHeight = 0;
                for (int i = 0; i < cells.size(); i++) {
                    Cell cell = cells.get(i);
                    if (cell instanceof TextCell || (cell instanceof ReplaceCell && !((ReplaceCell)cell).isRound())) {
                        if (cell.getHeight() > maxHeight) {
                            maxHeight = cell.getHeight();
                        }
                    }
                }
                updateHeight(maxHeight);
                return maxHeight;
            }
            return 0;
        }

        public void updateHeight(int height){
            if (cells != null) {
                for (int i = 0; i < cells.size(); i++) {
                    Cell cell = cells.get(i);
                    cell.lineHeight = height;
                }
            }
        }
    }

    class ReplaceCell extends Cell {
        private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private boolean isRound = false;

        public ReplaceCell(boolean isRound){
            this.isRound = isRound;
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            mPaint.setColor(Color.RED);
            canvas.drawRect(x, lineY, x + getWidth(), lineY + getHeight(), mPaint);
        }

        @Override
        public int getWidth() {
            return 100;
        }

        @Override
        public int getHeight() {
            return 100;
        }

        public boolean isRound(){
            return isRound;
        }
    }

    class TextCell extends Cell {

        private String text;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        public TextCell(String text){
            this.text = text;
            paint.setTextSize(20);
        }

        public TextCell(String text, int fontSize){
            this.text = text;
            paint.setTextSize(fontSize);
        }

        public TextCell(String text, Paint paint){
            this.text = text;
            this.paint = paint;
        }

        @Override
        public List<Cell> getSubCells() {
            if (!TextUtils.isEmpty(text)) {
                List<Cell> subCells = new ArrayList<>();
                for (int i = 0; i < text.length(); i++) {
                    String word = text.substring(i, i + 1);
                    TextCell cell = new TextCell(word, paint);
                    subCells.add(cell);
                }
                return subCells;
            }
            return null;
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            canvas.drawText(text, x, lineY + lineHeight - 10, paint);
        }

        @Override
        public int getWidth() {
            return (int) paint.measureText(text);
        }

        @Override
        public int getHeight() {
            Paint.FontMetrics fm = paint.getFontMetrics();
            return (int) Math.ceil(fm.descent - fm.top) + 12;
        }
    }

    class Cell {
        public int x, lineY;
        public int lineHeight;
        public void draw(Canvas canvas){}
        public int getWidth(){
            return 0;
        }
        public int getHeight(){
            return 0;
        }

        public List<Cell> getSubCells(){
            return null;
        }

//        public Rect getRect(){
//            return new Rect(x, lineY, x+ getWidth(), lineY + lineHeight);
//        }
    }
}
