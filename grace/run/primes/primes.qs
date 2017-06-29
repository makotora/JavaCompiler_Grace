1: unit,prime_2,-,-

prime_2:
	push ebp
	mov ebp, esp
	sub esp, 28

2: <,n,0,5
	mov eax, DWORD PTR [ebp + 16]
	mov edx, 0
	cmp eax, edx
	jl L5

3: jump,-,-,11
	jmp L11

4: noop,-,-,-

5: par,-1,V,-
	mov eax, -1
	push eax

6: par,$2,RET,-
	lea esi, DWORD PTR [ebp - 12]
	push esi

7: call,-,-,prime_2
	push DWORD PTR [ebp + 8]
	call prime_2
	add esp, 12

8: :=,$2,-,$$
	mov eax, DWORD PTR [ebp - 12]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax

9: ret,-,-,-
	jmp prime_2_end

10: jump,-,-,41
	jmp L41

11: <,n,2,13
	mov eax, DWORD PTR [ebp + 16]
	mov edx, 2
	cmp eax, edx
	jl L13

12: jump,-,-,16
	jmp L16

13: :=,0,-,$$
	mov eax, 0
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax

14: ret,-,-,-
	jmp prime_2_end

15: jump,-,-,41
	jmp L41

16: =,n,2,18
	mov eax, DWORD PTR [ebp + 16]
	mov edx, 2
	cmp eax, edx
	je L18

17: jump,-,-,21
	jmp L21

18: :=,1,-,$$
	mov eax, 1
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax

19: ret,-,-,-
	jmp prime_2_end

20: jump,-,-,41
	jmp L41

21: mod,n,2,$3
	mov eax, DWORD PTR [ebp + 16]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 16], edx

22: =,$3,0,24
	mov eax, DWORD PTR [ebp - 16]
	mov edx, 0
	cmp eax, edx
	je L24

23: jump,-,-,27
	jmp L27

24: :=,0,-,$$
	mov eax, 0
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax

25: ret,-,-,-
	jmp prime_2_end

26: jump,-,-,41
	jmp L41

27: :=,3,-,i
	mov eax, 3
	mov DWORD PTR [ebp - 4], eax

28: div,n,2,$4
	mov eax, DWORD PTR [ebp + 16]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 20], eax

29: <=,i,$4,31
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp - 20]
	cmp eax, edx
	jle L31

30: jump,-,-,39
	jmp L39

31: mod,n,i,$5
	mov eax, DWORD PTR [ebp + 16]
	cdq
	mov ebx, DWORD PTR [ebp - 4]
	idiv ebx
	mov DWORD PTR [ebp - 24], edx

32: =,$5,0,34
	mov eax, DWORD PTR [ebp - 24]
	mov edx, 0
	cmp eax, edx
	je L34

33: jump,-,-,36
	jmp L36

34: :=,0,-,$$
	mov eax, 0
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax

35: ret,-,-,-
	jmp prime_2_end

36: +,i,2,$6
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 2
	add eax, edx
	mov DWORD PTR [ebp - 28], eax

37: :=,$6,-,i
	mov eax, DWORD PTR [ebp - 28]
	mov DWORD PTR [ebp - 4], eax

38: jump,-,-,28
	jmp L28

39: :=,1,-,$$
	mov eax, 1
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax

40: ret,-,-,-
	jmp prime_2_end

41: endu,prime_2,-,-

prime_2_end:
	mov esp, ebp
	pop ebp
	ret

42: unit,main_1,-,-

main:
	push ebp
	mov ebp, esp
	sub esp, 60

43: par,"Limit: ",R,-
	mov esi, OFFSET FLAT:STR1
	push esi

44: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

45: par,$7,RET,-
	lea esi, DWORD PTR [ebp - 16]
	push esi

46: call,-,-,geti_1
	push 0
	call geti_1
	add esp, 8

47: :=,$7,-,limit
	mov eax, DWORD PTR [ebp - 16]
	mov DWORD PTR [ebp - 4], eax

48: par,"Primes:\n",R,-
	mov esi, OFFSET FLAT:STR2
	push esi

49: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

50: :=,0,-,counter
	mov eax, 0
	mov DWORD PTR [ebp - 12], eax

51: >=,limit,2,53
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 2
	cmp eax, edx
	jge L53

52: jump,-,-,59
	jmp L59

53: +,counter,1,$8
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 20], eax

54: :=,$8,-,counter
	mov eax, DWORD PTR [ebp - 20]
	mov DWORD PTR [ebp - 12], eax

55: par,2,V,-
	mov eax, 2
	push eax

56: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

57: par,"\n",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

58: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

59: >=,limit,3,61
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 3
	cmp eax, edx
	jge L61

60: jump,-,-,67
	jmp L67

61: +,counter,1,$9
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 24], eax

62: :=,$9,-,counter
	mov eax, DWORD PTR [ebp - 24]
	mov DWORD PTR [ebp - 12], eax

63: par,3,V,-
	mov eax, 3
	push eax

64: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

65: par,"\n",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

66: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

67: :=,6,-,number
	mov eax, 6
	mov DWORD PTR [ebp - 8], eax

68: <=,number,limit,70
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 4]
	cmp eax, edx
	jle L70

69: jump,-,-,101
	jmp L101

70: -,number,1,$10
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 28], eax

71: par,$10,V,-
	mov eax, DWORD PTR [ebp - 28]
	push eax

72: par,$11,RET,-
	lea esi, DWORD PTR [ebp - 32]
	push esi

73: call,-,-,prime_2
	push ebp
	call prime_2
	add esp, 12

74: =,$11,1,76
	mov eax, DWORD PTR [ebp - 32]
	mov edx, 1
	cmp eax, edx
	je L76

75: jump,-,-,83
	jmp L83

76: +,counter,1,$12
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 36], eax

77: :=,$12,-,counter
	mov eax, DWORD PTR [ebp - 36]
	mov DWORD PTR [ebp - 12], eax

78: -,number,1,$13
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 40], eax

79: par,$13,V,-
	mov eax, DWORD PTR [ebp - 40]
	push eax

80: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

81: par,"\n",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

82: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

83: #,number,limit,85
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 4]
	cmp eax, edx
	jne L85

84: jump,-,-,98
	jmp L98

85: +,number,1,$14
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 44], eax

86: par,$14,V,-
	mov eax, DWORD PTR [ebp - 44]
	push eax

87: par,$15,RET,-
	lea esi, DWORD PTR [ebp - 48]
	push esi

88: call,-,-,prime_2
	push ebp
	call prime_2
	add esp, 12

89: =,$15,1,91
	mov eax, DWORD PTR [ebp - 48]
	mov edx, 1
	cmp eax, edx
	je L91

90: jump,-,-,98
	jmp L98

91: +,counter,1,$16
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 52], eax

92: :=,$16,-,counter
	mov eax, DWORD PTR [ebp - 52]
	mov DWORD PTR [ebp - 12], eax

93: +,number,1,$17
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 56], eax

94: par,$17,V,-
	mov eax, DWORD PTR [ebp - 56]
	push eax

95: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

96: par,"\n",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

97: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

98: +,number,6,$18
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 6
	add eax, edx
	mov DWORD PTR [ebp - 60], eax

99: :=,$18,-,number
	mov eax, DWORD PTR [ebp - 60]
	mov DWORD PTR [ebp - 8], eax

100: jump,-,-,68
	jmp L68

101: par,"\nTotal: ",R,-
	mov esi, OFFSET FLAT:STR4
	push esi

102: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

103: par,counter,V,-
	mov eax, DWORD PTR [ebp - 12]
	push eax

104: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

105: par,"\n",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

106: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

107: endu,main_1,-,-

main_1_end:
	mov esp, ebp
	pop ebp
	ret

