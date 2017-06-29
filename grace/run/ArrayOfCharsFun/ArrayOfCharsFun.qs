1: unit,addToString_2,-,-

addToString_2:
	push ebp
	mov ebp, esp
	sub esp, 29

2: par,x,R,-
	mov esi, DWORD PTR [ebp + 16]
	push esi

3: par,$1,RET,-
	lea esi, DWORD PTR [ebp - 8]
	push esi

4: call,-,-,strlen_1
	push 0
	call strlen_1
	add esp, 12

5: :=,$1,-,size
	mov eax, DWORD PTR [ebp - 8]
	mov esi, DWORD PTR [ebp + 8]
	mov DWORD PTR [esi - 8], eax

6: :=,0,-,i
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax

7: <,i,size,9
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 8]
	mov edx, DWORD PTR [esi - 8]
	cmp eax, edx
	jl L9

8: jump,-,-,22
	jmp L22

9: array,x,i,$2
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 12], edx

10: array,x,i,$3
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 16], edx

11: par,[$3],V,-
	mov edi, DWORD PTR [ebp - 16]
	mov eax, BYTE PTR [edi]
	push eax

12: par,$4,RET,-
	lea esi, DWORD PTR [ebp - 20]
	push esi

13: call,-,-,ord_1
	push 0
	call ord_1
	add esp, 12

14: +,$4,1,$5
	mov eax, DWORD PTR [ebp - 20]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 24], eax

15: par,$5,V,-
	mov eax, DWORD PTR [ebp - 24]
	push eax

16: par,$6,RET,-
	lea esi, BYTE PTR [ebp - 25]
	push esi

17: call,-,-,chr_1
	push 0
	call chr_1
	add esp, 12

18: :=,$6,-,[$2]
	mov eax, BYTE PTR [ebp - 25]
	mov edi, DWORD PTR [ebp - 12]
	mov BYTE PTR [edi], eax

19: +,i,1,$7
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 29], eax

20: :=,$7,-,i
	mov eax, DWORD PTR [ebp - 29]
	mov DWORD PTR [ebp - 4], eax

21: jump,-,-,7
	jmp L7

22: endu,addToString_2,-,-

addToString_2_end:
	mov esp, ebp
	pop ebp
	ret

23: unit,main_1,-,-

main:
	push ebp
	mov ebp, esp
	sub esp, 180

24: par,"This program adds 1 to each char of a given string\n",R,-
	mov esi, OFFSET FLAT:STR1
	push esi

25: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

26: par,"Insert a string (max 20 chars) : ",R,-
	mov esi, OFFSET FLAT:STR2
	push esi

27: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

28: par,20,V,-
	mov eax, 20
	push eax

29: par,string,R,-
	lea esi, BYTE PTR [ebp - 28]
	push esi

30: call,-,-,gets_1
	sub esp, 4
	push 0
	call gets_1
	add esp, 16

31: par,"before: ",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

32: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

33: par,string,R,-
	lea esi, BYTE PTR [ebp - 28]
	push esi

34: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

35: par,'\n',V,-
	mov eax, '\n'
	push eax

36: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

37: par,string,R,-
	lea esi, BYTE PTR [ebp - 28]
	push esi

38: call,-,-,addToString_2
	sub esp, 4
	push ebp
	call addToString_2
	add esp, 12

39: par,"after: ",R,-
	mov esi, OFFSET FLAT:STR4
	push esi

40: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

41: par,string,R,-
	lea esi, BYTE PTR [ebp - 28]
	push esi

42: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

43: par,'\n',V,-
	mov eax, '\n'
	push eax

44: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

45: par,"String\'s size is: ",R,-
	mov esi, OFFSET FLAT:STR5
	push esi

46: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

47: par,size,V,-
	mov eax, DWORD PTR [ebp - 8]
	push eax

48: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

49: par,'\n',V,-
	mov eax, '\n'
	push eax

50: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

51: par,"\nNow passing ABCD from an array of strings!\n",R,-
	mov esi, OFFSET FLAT:STR6
	push esi

52: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

53: noop,-,-,-

54: noop,-,-,-

55: array,array_of_strings,11,$10
	mov eax, 11
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 60], edx

56: :=,'A',-,[$10]
	mov eax, 'A'
	mov edi, DWORD PTR [ebp - 60]
	mov BYTE PTR [edi], eax

57: noop,-,-,-

58: noop,-,-,-

59: array,array_of_strings,10,$13
	mov eax, 10
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 72], edx

60: noop,-,-,-

61: noop,-,-,-

62: array,array_of_strings,11,$16
	mov eax, 11
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 84], edx

63: :=,[$16],-,[$13]
	mov edi, DWORD PTR [ebp - 84]
	mov eax, BYTE PTR [edi]
	mov edi, DWORD PTR [ebp - 72]
	mov BYTE PTR [edi], eax

64: noop,-,-,-

65: noop,-,-,-

66: array,array_of_strings,11,$19
	mov eax, 11
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 96], edx

67: :=,'B',-,[$19]
	mov eax, 'B'
	mov edi, DWORD PTR [ebp - 96]
	mov BYTE PTR [edi], eax

68: noop,-,-,-

69: noop,-,-,-

70: array,array_of_strings,12,$22
	mov eax, 12
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 108], edx

71: :=,'C',-,[$22]
	mov eax, 'C'
	mov edi, DWORD PTR [ebp - 108]
	mov BYTE PTR [edi], eax

72: noop,-,-,-

73: noop,-,-,-

74: array,array_of_strings,13,$25
	mov eax, 13
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 120], edx

75: :=,'D',-,[$25]
	mov eax, 'D'
	mov edi, DWORD PTR [ebp - 120]
	mov BYTE PTR [edi], eax

76: noop,-,-,-

77: noop,-,-,-

78: array,array_of_strings,14,$28
	mov eax, 14
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 132], edx

79: :=,'\n',-,[$28]
	mov eax, '\n'
	mov edi, DWORD PTR [ebp - 132]
	mov BYTE PTR [edi], eax

80: noop,-,-,-

81: noop,-,-,-

82: array,array_of_strings,15,$31
	mov eax, 15
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 144], edx

83: :=,'\0',-,[$31]
	mov eax, 0
	mov edi, DWORD PTR [ebp - 144]
	mov BYTE PTR [edi], eax

84: par,"before: ",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

85: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

86: noop,-,-,-

87: array,array_of_strings,10,$34
	mov eax, 10
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 156], edx

88: par,[$34],R,-
	mov esi, DWORD PTR [ebp - 156]
	push esi

89: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

90: par,'\n',V,-
	mov eax, '\n'
	push eax

91: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

92: noop,-,-,-

93: array,array_of_strings,10,$37
	mov eax, 10
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 168], edx

94: par,[$37],R,-
	mov esi, DWORD PTR [ebp - 168]
	push esi

95: call,-,-,addToString_2
	sub esp, 4
	push ebp
	call addToString_2
	add esp, 12

96: par,"after: ",R,-
	mov esi, OFFSET FLAT:STR4
	push esi

97: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

98: noop,-,-,-

99: array,array_of_strings,10,$40
	mov eax, 10
	lea ecx, BYTE PTR [ebp - 48]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 180], edx

100: par,[$40],R,-
	mov esi, DWORD PTR [ebp - 180]
	push esi

101: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

102: par,'\n',V,-
	mov eax, '\n'
	push eax

103: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

104: endu,main_1,-,-

main_1_end:
	mov esp, ebp
	pop ebp
	ret

