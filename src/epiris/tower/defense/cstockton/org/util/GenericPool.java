package epiris.tower.defense.cstockton.org.util;

import java.util.Collections;
import java.util.Stack;

import org.anddev.andengine.util.Debug;

/**
 * @author Valentin Milea
 * @author Nicolas Gramlich
 *
 * @since 22:19:55 - 31.08.2010
 * @param <T>
 */
public abstract class GenericPool<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final Stack<T> mAvailableItems = new Stack<T>();
	private int mUnrecycledCount;
	private final int mGrowth;

	// ===========================================================
	// Constructors
	// ===========================================================

	public GenericPool() {
		this(0);
	}

	public GenericPool(final int pInitialSize) {
		this(pInitialSize, 1);
	}

	public GenericPool(final int pInitialSize, final int pGrowth) {
		if(pGrowth < 0) {
			throw new IllegalArgumentException("pGrowth must be at least 0!");
		}

		mGrowth = pGrowth;

		if(pInitialSize > 0) {
			batchAllocatePoolItems(pInitialSize);
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public synchronized int getUnrecycledCount() {
		return mUnrecycledCount;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract T onAllocatePoolItem();

	// ===========================================================
	// Methods
	// ===========================================================

	protected void onHandleRecycleItem(final T pItem) {

	}

	protected T onHandleAllocatePoolItem() {
		return onAllocatePoolItem();
	}

	protected void onHandleObtainItem(final T pItem) {

	}

	public synchronized void batchAllocatePoolItems(final int pCount) {
		final Stack<T> availableItems = mAvailableItems;
		for(int i = pCount - 1; i >= 0; i--) {
			availableItems.push(onHandleAllocatePoolItem());
		}
	}

	public synchronized T obtainPoolItem() {
		final T item;

		if(mAvailableItems.size() > 0) {
			item = mAvailableItems.pop();
		} else {
			if(mGrowth == 1) {
				item = onHandleAllocatePoolItem();
			} else {
				batchAllocatePoolItems(mGrowth);
				item = mAvailableItems.pop();
			}
			Debug.i(getClass().getName() + "<" + item.getClass().getSimpleName() +"> was exhausted, with " + mUnrecycledCount + " item not yet recycled. Allocated " + mGrowth + " more.");
		}
		onHandleObtainItem(item);

		mUnrecycledCount++;
		return item;
	}

	public synchronized void recyclePoolItem(final T pItem) {
		if(pItem == null) {
			throw new IllegalArgumentException("Cannot recycle null item!");
		}

		onHandleRecycleItem(pItem);

		mAvailableItems.push(pItem);

		mUnrecycledCount--;

		if(mUnrecycledCount < 0) {
			Debug.e("More items recycled than obtained!");
		}
	}

	public synchronized void shufflePoolItems() {
		Collections.shuffle(mAvailableItems);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
