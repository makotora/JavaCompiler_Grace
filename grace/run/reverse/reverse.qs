1: unit,reverse_2,-,-

reverse_2:
	push ebp
	mov ebp, esp
	sub esp, 36

2: par,s,R,-
	mov esi, DWORD PTR [ebp + 16]
	push esi

3: par,$1,RET,-
	lea esi, DWORD PTR [ebp - 12]
	push esi

4: call,-,-,strlen_1
	push 0
	call strlen_1
	add esp, 12

5: :=,$1,-,l
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 8], eax

6: :=,0,-,i
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax

7: <,i,l,9
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp - 8]
	cmp eax, edx
	jl L9

8: jump,-,-,17
	jmp L17

9: array,r,i,$2
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 8]
	lea ecx, BYTE PTR [esi - 32]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 16], edx

10: -,l,i,$3
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 4]
	sub eax, edx
	mov DWORD PTR [ebp - 20], eax

11: -,$3,1,$4
	mov eax, DWORD PTR [ebp - 20]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 24], eax

12: array,s,$4,$5
	mov eax, DWORD PTR [ebp - 24]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 28], edx

13: :=,[$5],-,[$2]
	mov edi, DWORD PTR [ebp - 28]
	mov eax, BYTE PTR [edi]
	mov edi, DWORD PTR [ebp - 16]
	mov BYTE PTR [edi], eax

14: +,i,1,$6
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 32], eax

15: :=,$6,-,i
	mov eax, DWORD PTR [ebp - 32]
	mov DWORD PTR [ebp - 4], eax

16: jump,-,-,7
	jmp L7

17: array,r,i,$7
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 8]
	lea ecx, BYTE PTR [esi - 32]
	lea edx, [eax + ecx]
	mov DWORD PTR [ebp - 36], edx

18: :=,'\0',-,[$7]
	mov eax, 0
	mov edi, DWORD PTR [ebp - 36]
	mov BYTE PTR [edi], eax

19: endu,reverse_2,-,-

reverse_2_end:
	mov esp, ebp
	pop ebp
	ret

20: unit,main_1,-,-

main:
	push ebp
	mov ebp, esp
	sub esp, 32

21: par,"\n!dlrow olleH",R,-
	mov esi, OFFSET FLAT:STR1
	push esi

22: call,-,-,reverse_2
	sub esp, 4
	push ebp
	call reverse_2
	add esp, 12

23: par,r,R,-
	lea esi, BYTE PTR [ebp - 32]
	push esi

24: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

25: endu,main_1,-,-

main_1_end:
	mov esp, ebp
	pop ebp
	ret

