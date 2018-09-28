package mchehab.com.xor;

import android.content.Context;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class TFXOR {
    static{
        System.loadLibrary("tensorflow_inference");
    }

    private TensorFlowInferenceInterface inferenceInterface;
    private static final String MODEL_FILE = "file:///android_asset/tensorflow_lite_xor_nn.pb";
    private static final String INPUT_NODE = "dense_1_input";
    private static final String[] OUTPUT_NODES = {"dense_2/Sigmoid"};
    private static final String OUTPUT_NODE = "dense_2/Sigmoid";
    private static final long[] INPUT_SIZE = {1,2};//1 row two features
    private static final int OUTPUT_SIZE = 1;

    public TFXOR(Context context){
        inferenceInterface = new TensorFlowInferenceInterface(context.getAssets(), MODEL_FILE);
    }

    public float[] predict(float[] data){
        float[] result = new float[OUTPUT_SIZE];
        inferenceInterface.feed(INPUT_NODE, data, INPUT_SIZE);
        inferenceInterface.run(OUTPUT_NODES);
        inferenceInterface.fetch(OUTPUT_NODE, result);

        return result;
    }
}