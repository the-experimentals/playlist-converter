import { atom } from "jotai";
import ConverterState from "../data-models/converter-state";

export const converterStateAtom = atom<ConverterState>({
    spotifyToken: "",
    googleToken: ""
})
