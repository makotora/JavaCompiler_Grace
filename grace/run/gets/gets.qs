1: unit,main_1,-,-

main:
	push ebp
	mov ebp, esp
	sub esp, 318

2: par,$1,RET,-
	lea esi, BYTE PTR [ebp - 305]
	push esi

3: call,-,-,getc_1
	push 0
	call getc_1
	add esp, 8

4: par,$1,V,-
	mov eax, BYTE PTR [ebp - 305]
	push eax

5: par,$2,RET,-
	lea esi, DWORD PTR [ebp - 309]
	push esi

6: call,-,-,ord_1
	push 0
	call ord_1
	add esp, 12

7: par,$2,V,-
	mov eax, DWORD PTR [ebp - 309]
	push eax

8: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

9: par,$3,RET,-
	lea esi, DWORD PTR [ebp - 313]
	push esi

10: call,-,-,geti_1
	push 0
	call geti_1
	add esp, 8

11: par,$3,V,-
	mov eax, DWORD PTR [ebp - 313]
	push eax

12: par,$4,RET,-
	lea esi, BYTE PTR [ebp - 314]
	push esi

13: call,-,-,chr_1
	push 0
	call chr_1
	add esp, 12

14: par,$4,V,-
	mov eax, BYTE PTR [ebp - 314]
	push eax

15: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

16: ret,-,-,-
	jmp main_1_end

17: par,100,V,-
	mov eax, 100
	push eax

18: par,str,R,-
	lea esi, BYTE PTR [ebp - 104]
	push esi

19: call,-,-,gets_1
	sub esp, 4
	push 0
	call gets_1
	add esp, 16

20: par,100,V,-
	mov eax, 100
	push eax

21: par,str2,R,-
	lea esi, BYTE PTR [ebp - 204]
	push esi

22: call,-,-,gets_1
	sub esp, 4
	push 0
	call gets_1
	add esp, 16

23: par,str3,R,-
	lea esi, BYTE PTR [ebp - 304]
	push esi

24: par,str,R,-
	lea esi, BYTE PTR [ebp - 104]
	push esi

25: call,-,-,strcpy_1
	sub esp, 4
	push 0
	call strcpy_1
	add esp, 16

26: par,str,R,-
	lea esi, BYTE PTR [ebp - 104]
	push esi

27: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

28: par,str3,R,-
	lea esi, BYTE PTR [ebp - 304]
	push esi

29: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

30: par,'\n',V,-
	mov eax, '\n'
	push eax

31: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

32: par,str,R,-
	lea esi, BYTE PTR [ebp - 104]
	push esi

33: par,str2,R,-
	lea esi, BYTE PTR [ebp - 204]
	push esi

34: call,-,-,strcat_1
	sub esp, 4
	push 0
	call strcat_1
	add esp, 16

35: par,str,R,-
	lea esi, BYTE PTR [ebp - 104]
	push esi

36: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

37: ret,-,-,-
	jmp main_1_end

38: par,str,R,-
	lea esi, BYTE PTR [ebp - 104]
	push esi

39: par,$5,RET,-
	lea esi, DWORD PTR [ebp - 318]
	push esi

40: call,-,-,strlen_1
	push 0
	call strlen_1
	add esp, 12

41: par,$5,V,-
	mov eax, DWORD PTR [ebp - 318]
	push eax

42: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

43: endu,main_1,-,-

main_1_end:
	mov esp, ebp
	pop ebp
	ret

