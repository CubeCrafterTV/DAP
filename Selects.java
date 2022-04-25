public class Selects{
	public static void main(String[] args){
		int[] data = scanIntArray();
		int k;
		//scanIntArray returns null in case of error while reading input
		if(data==null || data.length == 0){
			System.out.println("Error: You have to enter at least one number, and can only enter numbers");
			return;
		}
		
		//Error handling
		if(args.length == 1){
			try{
				k = Integer.parseInt(args[0]);
				if(k<=0) throw new Exception();
			} catch(Exception e){
				System.out.println("You can only enter positive Integers");
				return;
			}
		} else {
			System.out.println("One Integer parameter has to be entered");
			return;
		}
	}
	public static void minHeapify(int[] data, int i, int n){
		
	}
	public static void buildMinHeap(int[] data){
		
	}
	public static int extractMin(int[] data, int n){
		
	}
	public static int heapSelect(int[] data, int k){
		
	}
	public static int[] scanIntArray(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<String>();
        while(scanner.hasNextLine()){
            String inputWord = scanner.nextLine();
            if(inputWord.equals("")) break;
            inputs.add(inputWord);
        }
        int[] array = new int[inputs.size()];
        for(int i = 0; i < array.length; i++){
            try{
                array[i] = Integer.parseInt(inputs.get(i));
            } catch(Exception e){
                return null;
            }
        }
        return array;
    }
}