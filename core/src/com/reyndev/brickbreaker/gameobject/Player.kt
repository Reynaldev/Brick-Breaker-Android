package com.reyndev.brickbreaker.gameobject

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle

class Player(
    tx: Texture,
    w: Float,
    h: Float,
    x: Float,
    y: Float
) : GameObject(tx, w, h, x, y) {

    init {
        rect = Rectangle()
            .also {
                it.width = this.w;
                it.height = this.h;
                it.x = this.x
                it.y = this.y
            }
    }

    override fun move(x: Float, y: Float) {
        rect.x = x - (rect.width / 2f)
    }

    override fun update(batch: Batch) {
        super.update(batch)
    }

    override fun dispose() {
        super.dispose()
    }
}
