package com.reyndev.brickbreaker

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.reyndev.brickbreaker.screen.MainMenuScreen

class Main : Game() {
    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()

        setScreen(MainMenuScreen(this))
    }

    override fun dispose() {
        batch.dispose()
    }
}