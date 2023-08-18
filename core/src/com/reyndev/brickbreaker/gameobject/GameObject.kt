package com.reyndev.brickbreaker.gameobject

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle

open class GameObject(
    val tx: Texture,
    val w: Float,
    val h: Float,
    val x: Float,
    val y: Float
) : Controller {
    // Get/Set
    var width: Float
        get() = rect.width
        set(value) { rect.width = value }

    lateinit var rect: Rectangle

    override fun move(x: Float, y: Float) {
        rect.x = x - (rect.width / 2f)
        rect.y = y - (rect.height / 2f)
    }

    override fun update(batch: Batch) {
        batch.draw(tx, rect.x, rect.y, rect.width, rect.height)
    }

    override fun dispose() {
        tx.dispose()
    }
}
