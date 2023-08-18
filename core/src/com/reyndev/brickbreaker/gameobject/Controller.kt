package com.reyndev.brickbreaker.gameobject

import com.badlogic.gdx.graphics.g2d.Batch

interface Controller {
    fun move(x: Float, y: Float)
    fun update(batch: Batch)
    fun dispose()
}