package com.reyndev.brickbreaker.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.reyndev.brickbreaker.Main
import com.reyndev.brickbreaker.gameobject.Player

class GameScreen(private val game: Main) : Screen {
    private lateinit var camera: OrthographicCamera

    private lateinit var stage: Stage
    private lateinit var layout: Table

    // Default screen resolution
    private var width: Float = 720f
    private var height: Float = 1600f

    // Player
    private lateinit var player: Player

    init {
        // Stage init
        stage = Stage(ExtendViewport(width, height, OrthographicCamera()), game.batch)
        Gdx.input.inputProcessor = stage

        width = stage.viewport.worldWidth
        height = stage.viewport.worldHeight

        // Layout init
        layout = Table()
            .also {
                it.setFillParent(true)
                it.align(Align.top)
                stage.addActor(it)
            }

        // Player
        player = Player(
            Texture("gfx/paddle.png"),
            256f,
            64f,
            (width - 256f) / 2f,
            200f
        )
    }

    override fun show() {}

    override fun render(delta: Float) {
        // Clear frame buffer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        ScreenUtils.clear(0f, 0f, 1f, 1f)

        // Draw stage
        stage.act(delta)
        stage.draw()

        // Batch
        stage.batch.begin()
        player.update(stage.batch)
        stage.batch.end()

        // Input
        if (Gdx.input.isTouched) {
            // Touch position
            val touchPos = Vector3()
                .also {
                    it.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
                    stage.camera.unproject(it)
                }

            player.move(touchPos.x, 0f)
        }

        // Player logic
        // Player border
        if (player.rect.x < 0f)
            player.rect.x = 0f
        if (player.rect.x > (width - player.width))
            player.rect.x = width - player.width
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        // Destroy player
        stage.dispose()
        player.dispose()
    }
}