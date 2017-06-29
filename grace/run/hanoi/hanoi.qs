1: unit,move_3,-,-

move_3:
	push ebp
	mov ebp, esp
	sub esp, 0

2: par,"Moving from ",R,-
	mov esi, OFFSET FLAT:STR1
	push esi

3: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

4: par,source,R,-
	mov esi, DWORD PTR [ebp + 20]
	push esi

5: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

6: par," to ",R,-
	mov esi, OFFSET FLAT:STR2
	push esi

7: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

8: par,target,R,-
	mov esi, DWORD PTR [ebp + 16]
	push esi

9: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

10: par,".\n",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

11: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

12: endu,move_3,-,-

move_3_end:
	mov esp, ebp
	pop ebp
	ret

13: unit,hanoi_2,-,-

hanoi_2:
	push ebp
	mov ebp, esp
	sub esp, 8

14: >=,rings,1,16
	mov eax, DWORD PTR [ebp + 28]
	mov edx, 1
	cmp eax, edx
	jge L16

15: jump,-,-,31
	jmp L31

16: -,rings,1,$1
	mov eax, DWORD PTR [ebp + 28]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 4], eax

17: par,$1,V,-
	mov eax, DWORD PTR [ebp - 4]
	push eax

18: par,source,R,-
	mov esi, DWORD PTR [ebp + 24]
	push esi

19: par,auxiliary,R,-
	mov esi, DWORD PTR [ebp + 16]
	push esi

20: par,target,R,-
	mov esi, DWORD PTR [ebp + 20]
	push esi

21: call,-,-,hanoi_2
	sub esp, 4
	push DWORD PTR [ebp + 8]
	call hanoi_2
	add esp, 24

22: par,source,R,-
	mov esi, DWORD PTR [ebp + 24]
	push esi

23: par,target,R,-
	mov esi, DWORD PTR [ebp + 20]
	push esi

24: call,-,-,move_3
	sub esp, 4
	push ebp
	call move_3
	add esp, 16

25: -,rings,1,$2
	mov eax, DWORD PTR [ebp + 28]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 8], eax

26: par,$2,V,-
	mov eax, DWORD PTR [ebp - 8]
	push eax

27: par,auxiliary,R,-
	mov esi, DWORD PTR [ebp + 16]
	push esi

28: par,target,R,-
	mov esi, DWORD PTR [ebp + 20]
	push esi

29: par,source,R,-
	mov esi, DWORD PTR [ebp + 24]
	push esi

30: call,-,-,hanoi_2
	sub esp, 4
	push DWORD PTR [ebp + 8]
	call hanoi_2
	add esp, 24

31: endu,hanoi_2,-,-

hanoi_2_end:
	mov esp, ebp
	pop ebp
	ret

32: unit,main_1,-,-

main:
	push ebp
	mov ebp, esp
	sub esp, 8

33: par,"Rings: ",R,-
	mov esi, OFFSET FLAT:STR4
	push esi

34: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

35: par,$3,RET,-
	lea esi, DWORD PTR [ebp - 8]
	push esi

36: call,-,-,geti_1
	push 0
	call geti_1
	add esp, 8

37: :=,$3,-,NumberOfRings
	mov eax, DWORD PTR [ebp - 8]
	mov DWORD PTR [ebp - 4], eax

38: par,$3,V,-
	mov eax, DWORD PTR [ebp - 8]
	push eax

39: par,"left",R,-
	mov esi, OFFSET FLAT:STR5
	push esi

40: par,"right",R,-
	mov esi, OFFSET FLAT:STR6
	push esi

41: par,"middle",R,-
	mov esi, OFFSET FLAT:STR7
	push esi

42: call,-,-,hanoi_2
	sub esp, 4
	push ebp
	call hanoi_2
	add esp, 24

43: endu,main_1,-,-

main_1_end:
	mov esp, ebp
	pop ebp
	ret

