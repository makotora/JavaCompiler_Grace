.intel_syntax noprefix
.text
	.global main
L1:
foo_2:
	push ebp
	mov ebp, esp
	sub esp, 38
L2:
	mov eax, 1
	mov ecx, 1
	imul ecx
	mov DWORD PTR [ebp - 30], ecx
L3:
	mov eax, 0
	mov edx, DWORD PTR [ebp - 30]
	add eax, edx
	mov DWORD PTR [ebp - 34], eax
L4:
	mov eax, DWORD PTR [ebp - 34]
	mov esi, DWORD PTR [ebp + 8]
	lea ecx, BYTE PTR [esi - 96]
	add eax, ecx
	mov DWORD PTR [ebp - 38], eax
L5:
	mov edi, DWORD PTR [ebp - 38]
	mov eax, BYTE PTR [edi]
	mov esi, DWORD PTR [ebp + 8]
	mov BYTE PTR [esi - 93], eax
L6:
	mov eax, 'a'
	mov esi, DWORD PTR [ebp + 8]
	mov BYTE PTR [esi - 93], eax
L7:
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L8:
	jmp foo_2_end
L9:
foo_2_end:
	mov esp, ebp
	pop ebp
	ret
L10:
foo1_2:
	push ebp
	mov ebp, esp
	sub esp, 0
L11:
	mov eax, DWORD PTR [ebp + 16]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
L12:
	jmp foo1_2_end
L13:
foo1_2_end:
	mov esp, ebp
	pop ebp
	ret
L14:
main:
	push ebp
	mov ebp, esp
	sub esp, 588
L15:
	mov eax, DWORD PTR [ebp - 4]
	push eax
L16:
	lea esi, DWORD PTR [ebp - 588]
	push esi
L17:
	push ebp
	call foo1_2
	add esp, 12
L18:
	mov eax, DWORD PTR [ebp - 588]
	mov DWORD PTR [ebp - 4], eax
L19:
main_1_end:
	mov esp, ebp
	pop ebp
	ret
	puts_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, OFFSET FLAT:printStr
	push eax
	call printf
	add esp, 8
	mov esp, ebp
	pop ebp
	ret

.data
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
