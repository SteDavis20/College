;
; CS1022 Introduction to Computing II 2019/2020
; Timer Example
;

; Putting break point at line 46 (STOP B STOP line), makes t1 (seen at bottom right) = 5.0000008000

T0TCR	EQU	0xE0004004
T0MR0	EQU	0xE0004018
T0MCR	EQU	0xE0004014

	AREA	RESET, CODE, READONLY
	ENTRY

	; Set match register for 5 secs using Match Register
	; Assuming a 1Mhz clock input to TIMER0, set MR
	; MR0 (0xE0004018) to 5,000,000
	LDR	R4, =T0MR0
	LDR	R5, =5000000
	STR	R5, [R4]
	LDR	R5, [R4]

	; Stop on match using Match Control Register
	; Set bit 2 of MCR (0xE0004014) to 1 to stop the counter after
	; match (5 secs)
	LDR	R4, =T0MCR
	NOP			; on "real" hardware, we cannot place
				; an instruction at address 0x00000014
	LDR	R5, =0x04
	STRH	R5, [R4]

	; Start TIMER0 using the Timer Control Register
	; Set bit 0 of TCR (0xE0004004) to enable the timer
	LDR	R4, =T0TCR
	LDR	R5, =0x01
	STRB	R5, [R4]


	; Keep testing bit 0 of the Timer Control Register (0xE0004004)
	; until the 1 that we set above becomes 0 again, indicating that
	; the timer has stopped
whWait
	LDR	R4, =T0TCR
	LDRB	R5, [R4]
	TST	R5, #1
	BNE	whWait

STOP	B	STOP

	END
