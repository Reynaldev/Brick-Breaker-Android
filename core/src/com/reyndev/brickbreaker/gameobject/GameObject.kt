package com.reyndev.brickbreaker.gameobject

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

open class GameObject(
    private val tx: Texture,
    private val w: Float,
    private val h: Float,
    private val x: Float,
    private val y: Float
) : Controller {
    // Get/Set
    var width: Float
        get() = rect.width
        set(value) { rect.width = value }

    var height: Float
        get() = rect.height
        set(value) { rect.height = value }

    var position: Vector3
        get() = vec
        set(value) { vec = value }

    // Vars
    private lateinit var rect: Rectangle
    private lateinit var vec: Vector3

    init {
        vec = Vector3(x, y, 0f)
        rect = Rectangle(vec.x, vec.y, w, h)
    }

    override fun move(vec: Vector3) {
        this.position.x += vec.x
        this.position.y += vec.y
    }

    override fun update(batch: Batch) {
        batch.draw(tx, this.position.x, this.position.y, this.width, this.height)
    }

    override fun dispose() {
        tx.dispose()
    }
}
