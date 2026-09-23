import { StatusBar } from "expo-status-bar";
import { SafeAreaView, StyleSheet, Text, View } from "react-native";

const featureRoutes = [
  ["Account overview", "/account"],
  ["Statement and transactions", "/statements"],
  ["Bill discovery", "/bills/discovery"],
  ["Bill management", "/bills"],
  ["Wallet and funding", "/wallet"],
  ["Payment operations", "/payments"],
  ["Financial advisor", "/advisor"]
] as const;

/** App composition only. Feature components belong in src/modules. */
export default function App() {
  return (
    <SafeAreaView style={styles.page}>
      <Text style={styles.title}>Bill Pay Application</Text>
      <Text>Select a subsystem to begin implementation.</Text>
      <View style={styles.list}>
        {featureRoutes.map(([name, path]) => (
          <Text key={name}>{name}: {path}</Text>
        ))}
      </View>
      <StatusBar style="auto" />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  page: { flex: 1, padding: 24, gap: 12 },
  title: { fontSize: 24, fontWeight: "600" },
  list: { gap: 6 }
});
