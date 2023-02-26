#pragma once

#include "features/custom_shift_keys.h"

enum layers {
    L_BASE,
    L_SYMBOLS,
    L_SHORTCUTS,
    L_RGB,
};

#define SHORT_SPC LT(2, KC_SPC)

#define TMUX LGUI(KC_1)
#define BROWSER LGUI(KC_1)
#define INTELLIJ LGUI(KC_1)
#define VSCODE LGUI(KC_1)

#define FSTOP KC_DOT
#define DQUOTE KC_DOUBLE_QUOTE
#define MINUS KC_MINUS
#define SOLIDUS KC_SLSH
#define SEMICOLON KC_SEMICOLON

const custom_shift_key_t custom_shift_keys[] = {
  {FSTOP , KC_NO}, // Shift . is Nothing
  {MINUS , KC_NO}, // Shift - is Nothing
  {SOLIDUS , KC_NO}, // Shift / is Nothing
  {SEMICOLON , KC_NO}, // Shift ; is Nothing
};
uint8_t NUM_CUSTOM_SHIFT_KEYS =
    sizeof(custom_shift_keys) / sizeof(custom_shift_key_t);