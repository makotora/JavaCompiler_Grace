.intel_syntax noprefix
.text
	.global main
L1:
foo_2:
	push ebp
	mov ebp, esp
	sub esp, 4
L2:
	mov esi, OFFSET FLAT:STR1
	push esi
L3:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L4:
	mov eax, 0
	mov ecx, DWORD PTR [ebp + 20]
	add eax, ecx
	mov DWORD PTR [ebp - 4], eax
L5:
	mov eax, 'D'
	mov edi, DWORD PTR [ebp - 4]
	mov BYTE PTR [edi], eax
L6:
	mov esi, DWORD PTR [ebp + 20]
	push esi
L7:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L8:
foo_2_end:
	mov esp, ebp
	pop ebp
	ret
L9:
main:
	push ebp
	mov ebp, esp
	sub esp, 67
L10:
	mov eax, 0
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 39], eax
L11:
	mov eax, 'S'
	mov edi, DWORD PTR [ebp - 39]
	mov DWORD PTR [edi], eax
L12:
	mov eax, 1
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 43], eax
L13:
	mov eax, 'I'
	mov edi, DWORD PTR [ebp - 43]
	mov DWORD PTR [edi], eax
L14:
	mov eax, 2
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 47], eax
L15:
	mov eax, 'C'
	mov edi, DWORD PTR [ebp - 47]
	mov DWORD PTR [edi], eax
L16:
	mov eax, 3
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 51], eax
L17:
	mov eax, 'K'
	mov edi, DWORD PTR [ebp - 51]
	mov DWORD PTR [edi], eax
L18:
	mov eax, 4
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 55], eax
L19:
	mov eax, '\t'
	mov edi, DWORD PTR [ebp - 55]
	mov DWORD PTR [edi], eax
L20:
	mov eax, 5
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 59], eax
L21:
	mov eax, '.'
	mov edi, DWORD PTR [ebp - 59]
	mov DWORD PTR [edi], eax
L22:
	mov eax, 6
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 63], eax
L23:
	mov eax, '\n'
	mov edi, DWORD PTR [ebp - 63]
	mov DWORD PTR [edi], eax
L24:
	mov eax, 7
	lea ecx, BYTE PTR [ebp - 35]
	add eax, ecx
	mov DWORD PTR [ebp - 67], eax
L25:
	mov eax, 0
	mov edi, DWORD PTR [ebp - 67]
	mov DWORD PTR [edi], eax
L26:
	lea esi, BYTE PTR [ebp - 35]
	push esi
L27:
	lea esi, DWORD PTR [ebp - 4]
	push esi
L28:
	sub esp, 4
	push ebp
	call foo_2
	add esp, 16
L29:
	lea esi, BYTE PTR [ebp - 35]
	push esi
L30:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L31:
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
	STR1: .ASCIZ "here"
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
