package epiris.tower.defense.cstockton.org.map;

import java.util.ArrayList;

public class MapPath
{

    private final ArrayList<int[]> mSteps = new ArrayList<int[]>();

    public void reset()
    {
        mSteps.clear();
    }

    public int getLength() {
        return mSteps.size();
    }

    public int[] getCoords(final int pIndex) {
        return mSteps.get(pIndex);
    }

    public void append(final int pX, final int pY) {
        mSteps.add(new int[] { pX, pY});
    }

    public void prepend(final int pX, final int pY) {
        mSteps.add(0, new int[] { pX, pY});
    }
}

/*
public class MapPath
{

    private final ArrayList<Step> mSteps = new ArrayList<Step>();

    public void reset()
    {
        this.mSteps.clear();
    }

    public int getLength() {
        return this.mSteps.size();
    }

    public Step getStep(final int pIndex) {
        return this.mSteps.get(pIndex);
    }

    public MapDirection getDirectionToPreviousStep(final int pIndex) {
        if(pIndex == 0) {
            return null;
        } else {
            final int dX = this.getTileColumn(pIndex - 1) - this.getTileColumn(pIndex);
            final int dY = this.getTileRow(pIndex - 1) - this.getTileRow(pIndex);
            return MapDirection.fromDelta(dX, dY);
        }
    }

    public MapDirection getDirectionToNextStep(final int pIndex) {
        if(pIndex == this.getLength() - 1) {
            return null;
        } else {
            final int dX = this.getTileColumn(pIndex + 1) - this.getTileColumn(pIndex);
            final int dY = this.getTileRow(pIndex + 1) - this.getTileRow(pIndex);
            return MapDirection.fromDelta(dX, dY);
        }
    }

    public int getTileColumn(final int pIndex) {
        return this.getStep(pIndex).getTileColumn();
    }

    public int getTileRow(final int pIndex) {
        return this.getStep(pIndex).getTileRow();
    }

    public void append(final int pTileColumn, final int pTileRow) {
        this.append(new Step(pTileColumn, pTileRow));
    }

    public void append(final Step pStep) {
        this.mSteps.add(pStep);
    }

    public void prepend(final int pTileColumn, final int pTileRow) {
        this.prepend(new Step(pTileColumn, pTileRow));
    }

    public void prepend(final Step pStep) {
        this.mSteps.add(0, pStep);
    }

    public boolean contains(final int pTileColumn, final int pTileRow) {
        final ArrayList<Step> steps = this.mSteps;
        for(int i = steps.size() - 1; i >= 0; i--) {
            final Step step = steps.get(i);
            if(step.getTileColumn() == pTileColumn && step.getTileRow() == pTileRow) {
                return true;
            }
        }
        return false;
    }

    public int getFromTileRow() {
        return this.getTileRow(0);
    }

    public int getFromTileColumn() {
        return this.getTileColumn(0);
    }

    public int getToTileRow() {
        return this.getTileRow(this.mSteps.size() - 1);
    }

    public int getToTileColumn() {
        return this.getTileColumn(this.mSteps.size() - 1);
    }

    public class Step {

        private final int mTileColumn;
        private final int mTileRow;

        public Step(final int pTileColumn, final int pTileRow) {
            this.mTileColumn = pTileColumn;
            this.mTileRow = pTileRow;
        }

        public int getTileColumn() {
            return this.mTileColumn;
        }

        public int getTileRow() {
            return this.mTileRow;
        }

        @Override
        public int hashCode() {
            return this.mTileColumn << 16 + this.mTileRow;
        }

        @Override
        public boolean equals(final Object pOther) {
            if(this == pOther) {
                return true;
            }
            if(pOther == null) {
                return false;
            }
            if(this.getClass() != pOther.getClass()) {
                return false;
            }
            final Step other = (Step) pOther;
            if(this.mTileColumn != other.mTileColumn) {
                return false;
            }
            if(this.mTileRow != other.mTileRow) {
                return false;
            }
            return true;
        }
    }
}
*/