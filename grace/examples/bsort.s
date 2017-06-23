.intel_syntax noprefix
	.global main
	.text

L1:
	swap_3:
	push ebp
	mov ebp, esp
	sub esp, 4
L2:
	mov esi, DWORD [ebp + 20]
	mov eax, DWORD PTR [esi]
	mov DWORD PTR [ebp - 4], eax
L3:
	mov esi, DWORD [ebp + 16]
	mov eax, DWORD PTR [esi]
	mov esi, DWORD PTR [ebp + 20]
	mov DWORD PTR [esi], eax
L4:
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 16]
	mov DWORD PTR [esi], eax
L5:
	swap_3_end:
	mov esp, ebp
	pop ebp
	ret
L6:
	bsort_2:
	push ebp
	mov ebp, esp
	sub esp, 40
L7:
	mov eax, 1
	mov DWORD PTR [ebp - 4], eax
L8:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 0
	cmp eax, edx
	jg L10
L9:
	jmp L31
L10:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L11:
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax
L12:
	mov eax, DWORD PTR [ebp + 20]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 12], eax
L13:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 12]
	cmp eax, edx
	jl L15
L14:
	jmp L30
L15:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 16], eax
L16:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 20], eax
L17:
	mov eax, DWORD PTR [ebp - 20]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 24], eax
L18:
	mov edi, DWORD PTR [ebp - 16]
	mov eax, DWORD PTR [edi]
	mov edi, DWORD PTR [ebp - 24]
	mov edx, DWORD PTR [edi]
	cmp eax, edx
	jg L20
L19:
	jmp L27
L20:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 28], eax
L21:
	lea esi, DWORD PTR [ebp - 28]
	push esi
L22:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 32], eax
L23:
	mov eax, DWORD PTR [ebp - 32]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 36], eax
L24:
	lea esi, DWORD PTR [ebp - 36]
	push esi
L25:
	sub esp, 4
	push ebp
	call swap_3
	add esp, 16
L26:
	mov eax, 1
	mov DWORD PTR [ebp - 4], eax
L27:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 40], eax
L28:
	mov eax, DWORD PTR [ebp - 40]
	mov DWORD PTR [ebp - 8], eax
L29:
	jmp L12
L30:
	jmp L8
L31:
	bsort_2_end:
	mov esp, ebp
	pop ebp
	ret
L32:
	putArray_2:
	push ebp
	mov ebp, esp
	sub esp, 12
L33:
	mov esi, DWORD PTR [ebp + 24]
	push esi
L34:
	sub esp, 4
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	push DWORD PTR [esi + 8]
	call puts_1
	add esp, 12
L35:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L36:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp + 20]
	cmp eax, edx
	jl L38
L37:
	jmp L48
L38:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 0
	cmp eax, edx
	jg L40
L39:
	jmp L42
L40:
	push esi
L41:
	sub esp, 4
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	push DWORD PTR [esi + 8]
	call puts_1
	add esp, 12
L42:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 8], eax
L43:
	mov edi, DWORD PTR [ebp - 8]
	mov eax, DWORD PTR [edi]
	push eax
L44:
	sub esp, 4
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	push DWORD PTR [esi + 8]
	call puti_1
	add esp, 12
L45:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 12], eax
L46:
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 4], eax
L47:
	jmp L36
L48:
	push esi
L49:
	sub esp, 4
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	push DWORD PTR [esi + 8]
	call puts_1
	add esp, 12
L50:
	putArray_2_end:
	mov esp, ebp
	pop ebp
	ret
L51:
	main:
	push ebp
	mov ebp, esp
	sub esp, 96
L52:
	mov eax, 65
	mov DWORD PTR [ebp - 4], eax
L53:
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax
L54:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 16
	cmp eax, edx
	jl L56
L55:
	jmp L66
L56:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 137
	imul ecx
	mov DWORD PTR [ebp - 76], ecx
L57:
	mov eax, DWORD PTR [ebp - 76]
	mov edx, 220
	add eax, edx
	mov DWORD PTR [ebp - 80], eax
L58:
	mov eax, DWORD PTR [ebp - 80]
	mov edx, DWORD PTR [ebp - 8]
	add eax, edx
	mov DWORD PTR [ebp - 84], eax
L59:
	mov eax, DWORD PTR [ebp - 84]
	cdq
	mov ebx, 101
	idiv ebx
	mov DWORD PTR [ebp - 88], edx
L60:
	mov eax, DWORD PTR [ebp - 88]
	mov DWORD PTR [ebp - 4], eax
L61:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 72]
	add eax, ecx
	mov DWORD PTR [ebp - 92], eax
L62:
	mov eax, DWORD PTR [ebp - 4]
	mov edi, DWORD PTR [ebp - 92]
	mov DWORD PTR [edi], eax
L63:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 96], eax
L64:
	mov eax, DWORD PTR [ebp - 96]
	mov DWORD PTR [ebp - 8], eax
L65:
	jmp L54
L66:
	push esi
L67:
	mov eax, 16
	push eax
L68:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L69:
	sub esp, 4
	push ebp
	call putArray_2
	add esp, 20
L70:
	mov eax, 16
	push eax
L71:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L72:
	sub esp, 4
	push ebp
	call bsort_2
	add esp, 16
L73:
	push esi
L74:
	mov eax, 16
	push eax
L75:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L76:
	sub esp, 4
	push ebp
	call putArray_2
	add esp, 20
L77:
	main_1_end:
	mov esp, ebp
	pop ebp
	ret
