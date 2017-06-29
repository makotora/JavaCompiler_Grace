1: unit,boo_3,-,-

boo_3:
	push ebp
	mov ebp, esp
	sub esp, 172

2: par,"In boo function with i: ",R,-
	mov esi, OFFSET FLAT:STR1
	push esi

3: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

4: par,i,V,-
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	push eax

5: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

6: par,'\n',V,-
	mov eax, '\n'
	push eax

7: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

8: <,i,20,10
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	mov edx, 20
	cmp eax, edx
	jl L10

9: jump,-,-,45
	jmp L45

10: div,i,10,$1
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 84], eax

11: mod,i,10,$2
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 88], edx

12: *,$1,10,$3
	mov eax, DWORD PTR [ebp - 84]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 92], eax

13: +,$2,$3,$4
	mov eax, DWORD PTR [ebp - 88]
	mov edx, DWORD PTR [ebp - 92]
	add eax, edx
	mov DWORD PTR [ebp - 96], eax

14: array,x,$4,$5
	mov eax, DWORD PTR [ebp - 96]
	mov esi, DWORD PTR [ebp + 8]
	mov ecx, DWORD PTR [esi + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 100], edx

15: par,[$5],V,-
	mov edi, DWORD PTR [ebp - 100]
	mov eax, DWORD PTR [edi]
	push eax

16: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

17: par,' ',V,-
	mov eax, ' '
	push eax

18: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

19: div,i,10,$6
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 104], eax

20: mod,i,10,$7
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 108], edx

21: *,$6,10,$8
	mov eax, DWORD PTR [ebp - 104]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 112], eax

22: +,$7,$8,$9
	mov eax, DWORD PTR [ebp - 108]
	mov edx, DWORD PTR [ebp - 112]
	add eax, edx
	mov DWORD PTR [ebp - 116], eax

23: array,x,$9,$10
	mov eax, DWORD PTR [ebp - 116]
	mov esi, DWORD PTR [ebp + 8]
	mov ecx, DWORD PTR [esi + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 120], edx

24: noop,-,-,-

25: noop,-,-,-

26: noop,-,-,-

27: noop,-,-,-

28: array,x,$9,$15
	mov eax, DWORD PTR [ebp - 116]
	mov esi, DWORD PTR [ebp + 8]
	mov ecx, DWORD PTR [esi + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 140], edx

29: +,[$15],1,$16
	mov edi, DWORD PTR [ebp - 140]
	mov eax, DWORD PTR [edi]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 144], eax

30: :=,$16,-,[$10]
	mov eax, DWORD PTR [ebp - 144]
	mov edi, DWORD PTR [ebp - 120]
	mov DWORD PTR [edi], eax

31: noop,-,-,-

32: noop,-,-,-

33: noop,-,-,-

34: noop,-,-,-

35: array,x,$9,$21
	mov eax, DWORD PTR [ebp - 116]
	mov esi, DWORD PTR [ebp + 8]
	mov ecx, DWORD PTR [esi + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 164], edx

36: par,[$21],V,-
	mov edi, DWORD PTR [ebp - 164]
	mov eax, DWORD PTR [edi]
	push eax

37: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

38: par,'\n',V,-
	mov eax, '\n'
	push eax

39: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

40: par,'\n',V,-
	mov eax, '\n'
	push eax

41: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

42: +,i,1,$22
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov eax, DWORD PTR [esi - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 168], eax

43: :=,$22,-,i
	mov eax, DWORD PTR [ebp - 168]
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov DWORD PTR [esi - 4], eax

44: jump,-,-,8
	jmp L8

45: :=,100,-,j
	mov eax, 100
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov DWORD PTR [esi - 8], eax

46: par,x,R,-
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 16]
	push esi

47: par,$23,RET,-
	lea esi, DWORD PTR [ebp - 172]
	push esi

48: call,-,-,foo_2
	mov esi, DWORD PTR [ebp + 8]
	push DWORD PTR [esi + 8]
	call foo_2
	add esp, 12

49: :=,$23,-,ret
	mov eax, DWORD PTR [ebp - 172]
	mov esi, DWORD PTR [ebp + 8]
	mov esi, DWORD PTR [esi + 8]
	mov DWORD PTR [esi - 12], eax

50: par,"Here\n",R,-
	mov esi, OFFSET FLAT:STR2
	push esi

51: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

52: endu,boo_3,-,-

boo_3_end:
	mov esp, ebp
	pop ebp
	ret

53: unit,foo_2,-,-

foo_2:
	push ebp
	mov ebp, esp
	sub esp, 32

54: par,"In foo function with i: ",R,-
	mov esi, OFFSET FLAT:STR3
	push esi

55: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

56: par,i,V,-
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	push eax

57: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

58: par,'\n',V,-
	mov eax, '\n'
	push eax

59: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

60: par,"In foo function with j: ",R,-
	mov esi, OFFSET FLAT:STR4
	push esi

61: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

62: par,j,V,-
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 8]
	push eax

63: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

64: par,'\n',V,-
	mov eax, '\n'
	push eax

65: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

66: >,i,0,68
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	mov edx, 0
	cmp eax, edx
	jg L68

67: jump,-,-,93
	jmp L93

68: -,i,1,$24
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 4], eax

69: :=,$24,-,i
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 8]
	mov DWORD PTR [esi - 4], eax

70: div,$24,10,$25
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 8], eax

71: par,$25,V,-
	mov eax, DWORD PTR [ebp - 8]
	push eax

72: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

73: par,' ',V,-
	mov eax, ' '
	push eax

74: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

75: mod,i,10,$26
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 12], edx

76: par,$26,V,-
	mov eax, DWORD PTR [ebp - 12]
	push eax

77: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

78: par,' ',V,-
	mov eax, ' '
	push eax

79: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

80: div,i,10,$27
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 16], eax

81: mod,i,10,$28
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 20], edx

82: *,$27,10,$29
	mov eax, DWORD PTR [ebp - 16]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 24], eax

83: +,$28,$29,$30
	mov eax, DWORD PTR [ebp - 20]
	mov edx, DWORD PTR [ebp - 24]
	add eax, edx
	mov DWORD PTR [ebp - 28], eax

84: array,x,$30,$31
	mov eax, DWORD PTR [ebp - 28]
	mov ecx, DWORD PTR [ebp + 16]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 32], edx

85: par,[$31],V,-
	mov edi, DWORD PTR [ebp - 32]
	mov eax, DWORD PTR [edi]
	push eax

86: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

87: par,'\n',V,-
	mov eax, '\n'
	push eax

88: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

89: par,'\n',V,-
	mov eax, '\n'
	push eax

90: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

91: :=,'z',-,chr
	mov eax, 'z'
	mov esi, DWORD PTR [ebp + 8]
	mov BYTE PTR [esi - 93], eax

92: jump,-,-,66
	jmp L66

93: par,'\n',V,-
	mov eax, '\n'
	push eax

94: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

95: #,j,100,97
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 8]
	mov edx, 100
	cmp eax, edx
	jne L97

96: jump,-,-,104
	jmp L104

97: par,"Calling boo with i: ",R,-
	mov esi, OFFSET FLAT:STR5
	push esi

98: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

99: par,i,V,-
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	push eax

100: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

101: par,'\n',V,-
	mov eax, '\n'
	push eax

102: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

103: call,-,-,boo_3
	sub esp, 4
	push ebp
	call boo_3
	add esp, 8

104: :=,i,-,$$
	mov esi, DWORD PTR [ebp + 8]
	mov eax, DWORD PTR [esi - 4]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax

105: ret,-,-,-
	jmp foo_2_end

106: endu,foo_2,-,-

foo_2_end:
	mov esp, ebp
	pop ebp
	ret

107: unit,main_1,-,-

main:
	push ebp
	mov ebp, esp
	sub esp, 179

108: par,"1 + 24 = ",R,-
	mov esi, OFFSET FLAT:STR6
	push esi

109: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

110: noop,-,-,-

111: par,25,V,-
	mov eax, 25
	push eax

112: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

113: par,'\n',V,-
	mov eax, '\n'
	push eax

114: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

115: par,"51 - 100 = ",R,-
	mov esi, OFFSET FLAT:STR7
	push esi

116: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

117: noop,-,-,-

118: par,-49,V,-
	mov eax, -49
	push eax

119: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

120: par,'\n',V,-
	mov eax, '\n'
	push eax

121: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

122: par,"5 * 12 = ",R,-
	mov esi, OFFSET FLAT:STR8
	push esi

123: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

124: noop,-,-,-

125: par,60,V,-
	mov eax, 60
	push eax

126: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

127: par,'\n',V,-
	mov eax, '\n'
	push eax

128: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

129: par,"10 div 3 = ",R,-
	mov esi, OFFSET FLAT:STR9
	push esi

130: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

131: noop,-,-,-

132: par,3,V,-
	mov eax, 3
	push eax

133: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

134: par,'\n',V,-
	mov eax, '\n'
	push eax

135: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

136: par,"5 * 6 = ",R,-
	mov esi, OFFSET FLAT:STR10
	push esi

137: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

138: noop,-,-,-

139: par,30,V,-
	mov eax, 30
	push eax

140: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

141: par,'\n',V,-
	mov eax, '\n'
	push eax

142: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

143: par,'\n',V,-
	mov eax, '\n'
	push eax

144: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

145: par,'\n',V,-
	mov eax, '\n'
	push eax

146: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

147: :=,0,-,i
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax

148: <,i,20,150
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 20
	cmp eax, edx
	jl L150

149: jump,-,-,180
	jmp L180

150: div,i,10,$37
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 127], eax

151: par,$37,V,-
	mov eax, DWORD PTR [ebp - 127]
	push eax

152: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

153: par,' ',V,-
	mov eax, ' '
	push eax

154: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

155: mod,i,10,$38
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 131], edx

156: par,$38,V,-
	mov eax, DWORD PTR [ebp - 131]
	push eax

157: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

158: par,' ',V,-
	mov eax, ' '
	push eax

159: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

160: div,i,10,$39
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 135], eax

161: mod,i,10,$40
	mov eax, DWORD PTR [ebp - 4]
	cdq
	mov ebx, 10
	idiv ebx
	mov DWORD PTR [ebp - 139], edx

162: *,$39,10,$41
	mov eax, DWORD PTR [ebp - 135]
	mov ecx, 10
	imul ecx
	mov DWORD PTR [ebp - 143], eax

163: +,$40,$41,$42
	mov eax, DWORD PTR [ebp - 139]
	mov edx, DWORD PTR [ebp - 143]
	add eax, edx
	mov DWORD PTR [ebp - 147], eax

164: array,x,$42,$43
	mov eax, DWORD PTR [ebp - 147]
	lea ecx, DWORD PTR [ebp - 92]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 151], edx

165: :=,i,-,[$43]
	mov eax, DWORD PTR [ebp - 4]
	mov edi, DWORD PTR [ebp - 151]
	mov DWORD PTR [edi], eax

166: noop,-,-,-

167: noop,-,-,-

168: noop,-,-,-

169: noop,-,-,-

170: array,x,$42,$48
	mov eax, DWORD PTR [ebp - 147]
	lea ecx, DWORD PTR [ebp - 92]
	lea edx, [4*eax + ecx]
	mov DWORD PTR [ebp - 171], edx

171: par,[$48],V,-
	mov edi, DWORD PTR [ebp - 171]
	mov eax, DWORD PTR [edi]
	push eax

172: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

173: par,'\n',V,-
	mov eax, '\n'
	push eax

174: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

175: par,'\n',V,-
	mov eax, '\n'
	push eax

176: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

177: +,i,1,$49
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 175], eax

178: :=,$49,-,i
	mov eax, DWORD PTR [ebp - 175]
	mov DWORD PTR [ebp - 4], eax

179: jump,-,-,148
	jmp L148

180: :=,'a',-,chr
	mov eax, 'a'
	mov BYTE PTR [ebp - 93], eax

181: par,'a',V,-
	mov eax, 'a'
	push eax

182: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

183: par,'\n',V,-
	mov eax, '\n'
	push eax

184: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

185: par,"Calling foo with i: ",R,-
	mov esi, OFFSET FLAT:STR11
	push esi

186: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

187: par,i,V,-
	mov eax, DWORD PTR [ebp - 4]
	push eax

188: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

189: par,'\n',V,-
	mov eax, '\n'
	push eax

190: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

191: :=,0,-,j
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax

192: par,x,R,-
	lea esi, DWORD PTR [ebp - 92]
	push esi

193: par,$50,RET,-
	lea esi, DWORD PTR [ebp - 179]
	push esi

194: call,-,-,foo_2
	push ebp
	call foo_2
	add esp, 12

195: :=,$50,-,ret
	mov eax, DWORD PTR [ebp - 179]
	mov DWORD PTR [ebp - 12], eax

196: par,"Foo returned : ",R,-
	mov esi, OFFSET FLAT:STR12
	push esi

197: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

198: par,ret,V,-
	mov eax, DWORD PTR [ebp - 12]
	push eax

199: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

200: par,'\n',V,-
	mov eax, '\n'
	push eax

201: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

202: par,"i is : ",R,-
	mov esi, OFFSET FLAT:STR13
	push esi

203: call,-,-,puts_1
	sub esp, 4
	push 0
	call puts_1
	add esp, 12

204: par,i,V,-
	mov eax, DWORD PTR [ebp - 4]
	push eax

205: call,-,-,puti_1
	sub esp, 4
	push 0
	call puti_1
	add esp, 12

206: par,'\n',V,-
	mov eax, '\n'
	push eax

207: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

208: par,chr,V,-
	mov eax, BYTE PTR [ebp - 93]
	push eax

209: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

210: par,'\n',V,-
	mov eax, '\n'
	push eax

211: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

212: par,'\n',V,-
	mov eax, '\n'
	push eax

213: call,-,-,putc_1
	sub esp, 4
	push 0
	call putc_1
	add esp, 12

214: endu,main_1,-,-

main_1_end:
	mov esp, ebp
	pop ebp
	ret

