package mchehab.com.kotlin

import android.content.Context
import org.tensorflow.contrib.android.TensorFlowInferenceInterface

class TFXOR internal constructor(context: Context){

    companion object {
        init {
            System.loadLibrary("tensorflow_inference")
        }
    }

    private val inferenceInterface: TensorFlowInferenceInterface
    private val MODEL_FILE = "file:///android_asset/tensorflow_lite_xor_nn.pb"
    private val INPUT_NODE = "dense_1_input"
    private val OUTPUT_NODES = arrayOf("dense_2/Sigmoid")
    private val OUTPUT_NODE = "dense_2/Sigmoid"
    private val INPUT_SIZE = longArrayOf(1, 2)//1 row two features
    private val OUTPUT_SIZE = 1

    init {
        inferenceInterface = TensorFlowInferenceInterface(context.assets, MODEL_FILE)
    }

    fun predict(data: FloatArray): FloatArray {
        val result = FloatArray(OUTPUT_SIZE)
        inferenceInterface.feed(INPUT_NODE, data, *INPUT_SIZE)
        inferenceInterface.run(OUTPUT_NODES)
        inferenceInterface.fetch(OUTPUT_NODE, result)

        return result
    }
}