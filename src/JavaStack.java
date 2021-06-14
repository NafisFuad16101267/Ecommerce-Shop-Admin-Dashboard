import java.util.*;
public class JavaStack{

	public static void main(String []argh)
	{
		Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input=sc.next();
            //Complete the code
            boolean flag = true;
            Stack<Character> stack = new Stack<Character>();
            char c[] = input.toCharArray();
            for(int i = 0; i<c.length; i++){
                if(c[i]=='(' || c[i]=='{' || c[i]=='['){
                    stack.push(c[i]);
                } else {
                    if(stack.isEmpty()){
                        flag = false;
                        break;
                    }else{
                        char temp = stack.pop();
                        if(c[i] == temp){
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if(!stack.isEmpty()) flag = false;
            System.out.println(flag);
        }

	}
}
