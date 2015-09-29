public class ToneMatrixLogic {
	/**
	 * Given the contents of the tone matrix, returns a string of notes that should be played
	 * to represent that matrix.
	 * 
	 * @param toneMatrix The contents of the tone matrix.
	 * @param column The column number that is currently being played.
	 * @param samples The sound samples associated with each row.
	 * @return A sound sample corresponding to all notes currently being played.
	 */
	public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
		double[] result = new double[ToneMatrixConstants.sampleSize()];
		
		int height_toneMatrix = toneMatrix.length;
		
		for(int i = 0; i < height_toneMatrix; i++) {
			if(toneMatrix[i][column] == true) {
				for(int j = 0; j < ToneMatrixConstants.sampleSize(); j++) {
					result[j] = result[j] + samples[i][j];
				}
			}
		}
		
		result = normalize(result);
		
		return result;
	}
	
	private static double[] normalize(double[] array) {
		
		double temp = Math.abs(array[0]);
		
		for(int i = 0; i < ToneMatrixConstants.sampleSize(); i++) {
			if(Math.abs(array[i]) > temp) {
				temp = Math.abs(array[i]);
			}
		}
				
		for(int i = 0; i < ToneMatrixConstants.sampleSize(); i++) {
			if(temp != 0){
				array[i] = array[i] / temp;
			}
		}
		
		return array;
	}
}
