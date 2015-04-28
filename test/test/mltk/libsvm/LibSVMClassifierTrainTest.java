package test.mltk.libsvm;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import libsvm.svm_parameter;

import org.junit.Test;
import org.mltk.libsvm.LibSVMClassifierTrain;
import org.mltk.libsvm.LibSVMParameFactory;
import org.mltk.libsvm.model.TrainDataSet;

public class LibSVMClassifierTrainTest {

	/**
	 * 模拟数据集
	 * 
	 * @return
	 */
	public TrainDataSet createTrainData() {

		final String[] vec1Node1 = new String[] { "1", "0" };
		final String[] vec1Node2 = new String[] { "2", "0" };
		final List<String[]> vector1 = new ArrayList<String[]>() {
			{
				add(vec1Node1);
				add(vec1Node2);
			}
		};

		final String[] vec2Node1 = new String[] { "1", "1" };
		final String[] vec2Node2 = new String[] { "2", "1" };
		final List<String[]> vector2 = new ArrayList<String[]>() {
			{
				add(vec2Node1);
				add(vec2Node2);
			}
		};

		final String[] vec3Node1 = new String[] { "1", "0" };
		final String[] vec3Node2 = new String[] { "2", "1" };
		final List<String[]> vector3 = new ArrayList<String[]>() {
			{
				add(vec3Node1);
				add(vec3Node2);
			}
		};

		final String[] vec4Node1 = new String[] { "1", "1" };
		final String[] vec4Node2 = new String[] { "2", "2" };
		final List<String[]> vector4 = new ArrayList<String[]>() {
			{
				add(vec4Node1);
				add(vec4Node2);
			}
		};

		// 注意，普通的map不允许key值重复，要使用允许key值重复的特殊map
		Map<Double, List<String[]>> trainData = new IdentityHashMap<Double, List<String[]>>() {
			{
				put(-1.0, vector1);
				put(-1.0, vector2);
				put(1.0, vector3);
				put(1.0, vector4);

			}
		};

		TrainDataSet trainDataSet = new TrainDataSet(trainData, 4, 2);

		return trainDataSet;
	}

	@Test
	public void testLibSVMTrain() {

		TrainDataSet trainDataSet = this.createTrainData();

		LibSVMParameFactory parameFactory = new LibSVMParameFactory(
				svm_parameter.C_SVC, svm_parameter.RBF, 1000, 0.0000001, 10, 1,
				1024);
		LibSVMClassifierTrain classifierTrain = new LibSVMClassifierTrain(
				parameFactory);
		boolean trainFlag = classifierTrain.exec(
				"./file/libsvm/svm_model_file", trainDataSet);
		if (trainFlag) {
			System.err.println("SUCCESS");
		} else {
			System.err.println("FAILED");
		}
	}
}
