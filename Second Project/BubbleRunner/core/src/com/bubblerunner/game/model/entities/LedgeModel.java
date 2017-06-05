package com.bubblerunner.game.model.entities;

/**
 * Created by Mario on 04/06/2017.
 */


import com.bubblerunner.game.constants.Constants.LEDGE_LETHALITY;
import com.bubblerunner.game.utils.Point;

import static com.bubblerunner.game.model.entities.LedgeModel.LedgeSize.MEDIUM;

/**
     * A model representing a asteroid with a certain size.
     */
    public class LedgeModel extends EntityModel{
        /**
         * Possible asteroid sizes.
         */
        public enum LedgeSize {MEDIUM, SMALL}

        /**
         * This asteroid size.
         */
        private LedgeSize size;

        private final float width;

        private final float height;

        private LEDGE_LETHALITY lethality;

        private boolean spawnedAnother;

        /**
         * Constructs a asteroid model belonging to a game model.
         *
         * @param coordinates The x-coordinate of this asteroid.
         * @param size The size of this asteroid.
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
         * Returns the size of this asteroid.
         *
         * @return The size of this asteroid.
         */
        public LedgeSize getSize() {
            return size;
        }

        @Override
        public ModelType getType() {
            if (size == LedgeSize.SMALL)
                return ModelType.SMALLLEDGE;
            if (size == MEDIUM)
                return ModelType.MEDIUMLEDGE;
            return null;
        }


        public LEDGE_LETHALITY getLethality(){
            return this.lethality;
        }

        public boolean getSpawnedAnother(){
            return this.spawnedAnother;
        }

        public void setSpawnedAnother(boolean spawnedAnother){
            this.spawnedAnother = spawnedAnother;
        }

        public float getWidth(){return this.width;}

        public float getHeight(){return this.height;}

    }

