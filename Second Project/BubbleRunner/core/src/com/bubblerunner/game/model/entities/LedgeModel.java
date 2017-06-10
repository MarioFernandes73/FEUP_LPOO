package com.bubblerunner.game.model.entities;


import com.bubblerunner.game.utils.Constants.LEDGE_LETHALITY;
import com.bubblerunner.game.utils.Point;

import static com.bubblerunner.game.model.entities.LedgeModel.LedgeSize.MEDIUM;

/**
 * A model representing a asteroid with a certain size.
 */
public class LedgeModel extends EntityModel {

    /**
     * Possible ledges sizes.
     */
    public enum LedgeSize {
        MEDIUM, SMALL
    }

    /**
     * This ledge's size.
     */
    private LedgeSize size;

    /**
     * This ledge's width.
     */
    private final float width;

    /**
     * This ledge's height.
     */
    private final float height;

    /**
     * This ledge's lethality.
     */
    private LEDGE_LETHALITY lethality;

    /**
     * Flag which indicates if the event of creating a new ledge
     * due to this ledge reaching a certain height.
     */
    private boolean spawnedAnother;

    /**
     * Constructs a ledge model belonging to a game model.
     *
     * @param coordinates    The coordinates of this ledge.
     * @param size           The size of this ledge.
     * @param width          The width of this ledge.
     * @param height         The height of this ledge.
     * @param ledgeLethality The lethality of this ledge.
     */
    public LedgeModel(Point<Float> coordinates, LedgeSize size, float width, float height, LEDGE_LETHALITY ledgeLethality) {
        super(coordinates);
        this.size = size;
        this.width = width;
        this.height = height;
        this.lethality = ledgeLethality;
        this.spawnedAnother = false;
    }

    /**
     * Returns the size of this ledge.
     *
     * @return The size of this ledge.
     */
    public LedgeSize getSize() {
        return size;
    }

    /**
     * Returns the type of this ledge.
     *
     * @return The type of this ledge.
     */
    @Override
    public ModelType getType() {
        if (size == LedgeSize.SMALL)
            return ModelType.SMALLLEDGE;
        if (size == MEDIUM)
            return ModelType.MEDIUMLEDGE;
        return null;
    }

    /**
     * Returns the lethality of this ledge.
     *
     * @return The lethality of this ledge.
     */
    public LEDGE_LETHALITY getLethality() {
        return this.lethality;
    }

    /**
     * Sets the lethality of this ledge.
     *
     * @param lethality The lethality of this ledge.
     */
    public void setLethality(LEDGE_LETHALITY lethality) {
        this.lethality = lethality;
    }

    /**
     * Returns the spawnedAnother flag.
     *
     * @return The spawnedAnother flag
     */
    public boolean getSpawnedAnother() {
        return this.spawnedAnother;
    }

    /**
     * Sets the spawnedAnother flag.
     *
     * @param spawnedAnother The value of the spawnedAnother flag.
     */
    public void setSpawnedAnother(boolean spawnedAnother) {
        this.spawnedAnother = spawnedAnother;
    }

    /**
     * Returns the width of this ledge.
     *
     * @return The width of this ledge.
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Returns the height of this ledge.
     *
     * @return The height of this ledge.
     */
    public float getHeight() {
        return this.height;
    }

}

