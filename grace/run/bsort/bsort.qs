1: unit,swap_3,-,-

swap_3:
	push ebp
	mov ebp, esp
	sub esp, 4

2: :=,x,-,t
	mov esi, DWORD PTR [ebp + 20]
	mov eax, DWORD PTR [esi]
	mov DWORD PTR [ebp - 4], eax

3: :=,y,-,x
	mov esi, DWORD PTR [ebp + 16]
	mov eax, DWORD PTR [esi]
	mov esi, DWORD PTR [ebp + 20]
	mov DWORD PTR [esi], eax

4: :=,t,-,y
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 16]
	mov DWORD PTR [esi], eax

5: endu,swap_3,-,-

swap_3_end:
	mov esp, ebp
	pop ebp
	ret

6: unit,bsort_2,-,-

bsort_2:
	push ebp
	mov ebp, esp
	sub esp, 40

7: :=,1,-,changed
	mov eax, 1
	mov DWORD PTR [ebp - 4], eax

8: >,changed,0,10
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 0
	cmp eax, edx
	jg L10

9: jump,-,-,31
	jmp L31

10: :=,0,-,changed
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax

11: :=,0,-,i
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax

12: -,n,1,$1
	mov eax, DWORD PTR [ebp + 20]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 12], eax

13: <,i,$1,15
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 12]
	cmp eax, edx
	jl L15

14: jump,-,-,8
	jmp L8

15: array,x,i,$2
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 16], edx

16: +,i,1,$3
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 20], eax

17: array,x,$3,$4
	mov eax, DWORD PTR [ebp - 20]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 24], edx

18: >,[$2],[$4],20
	mov edi, DWORD PTR [ebp - 16]
	mov eax, DWORD PTR [edi]
	mov edi, DWORD PTR [ebp - 24]
	mov edx, DWORD PTR [edi]
	cmp eax, edx
	jg L20

19: jump,-,-,27
	jmp L27

20: array,x,i,$5
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 28], edx

21: par,[$5],R,-
	mov esi, DWORD PTR [ebp - 28]
	push esi

22: +,i,1,$6
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 32], eax

23: array,x,$6,$7
	mov eax, DWORD PTR [ebp - 32]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 36], edx

24: par,[$7],R,-
	mov esi, DWORD PTR [ebp - 36]
	push esi

25: call,-,-,swap_3
	sub esp, 4
	push ebp
	call swap_3
	add esp, 16

26: :=,1,-,changed
	mov eax, 1
	mov DWORD PTR [ebp - 4], eax

27: +,i,1,$8
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 40], eax

28: :=,$8,-,i
	mov eax, DWORD PTR [ebp - 40]
	mov DWORD PTR [ebp - 8], eax

29: jump,-,-,12
	jmp L12

30: noop,-,-,-

31: endu,bsort_2,-,-

bsort_2_end:
	mov esp, ebp
	pop ebp
	ret

32: unit,putArray_2,-,-

putArray_2:
	push ebp
	mov ebp, esp
	sub esp, 12

33: par,msg,R,-
	mov esi, DWORD PTR [ebp + 24]
	push esi

34: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

35: :=,0,-,i
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax

36: <,i,n,38
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp + 20]
	cmp eax, edx
	jl L38

37: jump,-,-,48
	jmp L48

38: >,i,0,40
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 0
	cmp eax, edx
	jg L40

39: jump,-,-,42
	jmp L42

40: par,", ",R,-
	mov esi, OFFSET FLAT:STR1
	push esi

41: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

42: array,x,i,$9
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 8], edx

43: par,[$9],V,-
	mov edi, DWORD PTR [ebp - 8]
	mov eax, DWORD PTR [edi]
	push eax

44: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

45: +,i,1,$10
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 12], eax

46: :=,$10,-,i
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 4], eax

47: jump,-,-,36
	jmp L36

48: par,"\n",R,-
	mov esi, OFFSET FLAT:STR2
	push esi

49: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

50: endu,putArray_2,-,-

putArray_2_end:
	mov esp, ebp
	pop ebp
	ret

51: unit,main_1,-,-

main:
	push ebp
	mov ebp, esp
	sub esp, 96

52: :=,65,-,seed
	mov eax, 65
	mov DWORD PTR [ebp - 4], eax

53: :=,0,-,i
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax

54: <,i,16,56
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 16
	cmp eax, edx
	jl L56

55: jump,-,-,66
	jmp L66

56: *,seed,137,$11
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 137
	imul ecx
	mov DWORD PTR [ebp - 76], eax

57: +,$11,220,$12
	mov eax, DWORD PTR [ebp - 76]
	mov edx, 220
	add eax, edx
	mov DWORD PTR [ebp - 80], eax

58: +,$12,i,$13
	mov eax, DWORD PTR [ebp - 80]
	mov edx, DWORD PTR [ebp - 8]
	add eax, edx
	mov DWORD PTR [ebp - 84], eax

59: mod,$13,101,$14
	mov eax, DWORD PTR [ebp - 84]
	cdq
	mov ebx, 101
	idiv ebx
	mov DWORD PTR [ebp - 88], edx

60: :=,$14,-,seed
	mov eax, DWORD PTR [ebp - 88]
	mov DWORD PTR [ebp - 4], eax

61: array,x,i,$15
	mov eax, DWORD PTR [ebp - 8]
	lea ecx, DWORD PTR [ebp - 72]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 92], edx

62: :=,$14,-,[$15]
	mov eax, DWORD PTR [ebp - 88]
	mov edi, DWORD PTR [ebp - 92]
	mov DWORD PTR [edi], eax

63: +,i,1,$16
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 96], eax

64: :=,$16,-,i
	mov eax, DWORD PTR [ebp - 96]
	mov DWORD PTR [ebp - 8], eax

65: jump,-,-,54
	jmp L54

66: par,"Initial array: ",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

67: par,16,V,-
	mov eax, 16
	push eax

68: par,x,R,-
	lea esi, DWORD PTR [ebp - 72]
	push esi

69: call,-,-,putArray_2
	sub esp, 4
	push ebp
	call putArray_2
	add esp, 20

70: par,16,V,-
	mov eax, 16
	push eax

71: par,x,R,-
	lea esi, DWORD PTR [ebp - 72]
	push esi

72: call,-,-,bsort_2
	sub esp, 4
	push ebp
	call bsort_2
	add esp, 16

73: par,"Sorted array: ",R,-
	mov esi, OFFSET FLAT:STR4
	push esi

74: par,16,V,-
	mov eax, 16
	push eax

75: par,x,R,-
	lea esi, DWORD PTR [ebp - 72]
	push esi

76: call,-,-,putArray_2
	sub esp, 4
	push ebp
	call putArray_2
	add esp, 20

77: endu,main_1,-,-

main_1_end:
	mov esp, ebp
	pop ebp
	ret

