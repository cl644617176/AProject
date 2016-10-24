package utils;

public class MidToPosT {

	// ������ŵ����ȼ�
	public static int Precedence(char sign) {
		switch (sign) {
		// +��-��Ϊ1
		case '+':
		case '-':
			return 1;
			// *��/Ϊ2
		case '*':
		case '/':
			return 2;
		case '(':
		case ')':
		default:
			return 0;
		}
	}

	/**************** ��׺����ʽת���ɺ�׺����ʽ ********************/
	public static String[] Change(String str) {
		String[] s2 = new String[Test.MAX_RANGE];
		// #Ϊ������
		String s1 = str + "#";
		// �����СΪ20��String���͵�ջ
		Stack<Character> T = new Stack<Character>(Test.MAX_RANGE);
		int i = 0, j = 0;
		char ch;
		String temp = " ";
		String number="";
		T.push('&');
		ch = s1.charAt(i);
		while (ch != '#') {
			// ����'('�ͽ�ջ
			if (ch == '(') {
				T.push(ch);
				ch = s1.charAt(++i);
			} else if (ch == ')') {
				// ����')'�Ͱ�ջ��'('��ķ���ȫ����ջ
				while (T.getTop() != '(')
					s2[j++] = String.valueOf(T.pop());
				T.pop();
				ch = s1.charAt(++i);
			} else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				char w = T.getTop();
				while (Precedence(w) >= Precedence(ch)) {
					s2[j++] = String.valueOf(w);
					T.pop();
					w = T.getTop();
				}
				T.push(ch);
				ch = s1.charAt(++i);
			} else {
				if ((ch >= '0' && ch <= '9') || ch == '.') {
					for(;s1.charAt(i)=='@'||(s1.charAt(i) >= '0' && s1.charAt(i) <= '9');i++){
						number=number+s1.charAt(i);
					}
					s2[j++] = number;
					number="";
					ch=s1.charAt(i);
				}
			}
		}
		ch = T.pop();
		while (ch != '&') {
			s2[j++] = String.valueOf(ch);
			ch = T.pop();
		}
		s2[j++] = "#";
		return s2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] postfix = Change("(25)+3*(6@2*2@5)");
		String[] infix=PostToMid.getInfixByPostfix(postfix);
		for (int i = 0; !postfix[i].equals("#"); i++) {
			System.out.println(postfix[i]);
		}
		for (int i = 0; !infix[i].equals("#"); i++) {
			System.out.print(infix[i]);
		}
	}

}